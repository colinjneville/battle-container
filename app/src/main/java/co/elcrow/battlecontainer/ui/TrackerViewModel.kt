package co.elcrow.battlecontainer.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import co.elcrow.battlecontainer.AppPreferences
import co.elcrow.battlecontainer.R
import co.elcrow.battlecontainer.Settings
import co.elcrow.battlecontainer.getSetting
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.Closeable
import kotlin.random.Random


private const val MAX_LIFE = 20
private const val INIT_LIFE = MAX_LIFE
private const val MAX_FORCE = 10
private const val INIT_FORCE = 2
private const val INIT_FORCE_POOL = 45
private const val FORCE_BONUS_LIFE_THRESHOLD = 7

private class UndoableAction(val tracker: TrackerViewModel) : Closeable {
    private var state: TrackerViewModel.State? = null

    init {
        if (tracker.undoLevel == 0) {
            state = tracker.state

        }
        tracker.undoLevel += 1
    }

    override fun close() {
        tracker.undoLevel -= 1

        tracker.pushState(state ?: return)
        assert(tracker.undoLevel == 0)
    }
}

data class Victory(val player: Player?, @StringRes val explanationId: Int?)

class Player(private val tracker: TrackerViewModel) {
    internal data class State(val life: Int, val force: Int)

    private val _life = MutableStateFlow(INIT_LIFE)
    val lifeFlow = _life.asStateFlow()
    var life: Int
        get() = lifeFlow.value
        set(value) {
            UndoableAction(tracker).use {
                val wasFinisherReady = isFinisherReady
                _life.value = value.coerceIn(0, MAX_LIFE)
                if (life == 0) {
                    val opponent = if (this == tracker.players[0]) tracker.players[1] else tracker.players[0]
                    tracker.victory = Victory(opponent, null)
                } else if (!wasFinisherReady && isFinisherReady) {
                    signalFinisherReady(tracker)
                }
                Unit
            }
        }

    private val _force = MutableStateFlow(INIT_FORCE)
    val forceFlow = _force.asStateFlow()
    var force: Int
        get() = forceFlow.value
        set(value) {
            UndoableAction(tracker).use {
                val wasFinisherReady = isFinisherReady
                val fromPool = (value - force).coerceAtLeast(0)
                _force.value = value.coerceIn(0, MAX_FORCE)
                tracker.forcePool -= fromPool
                if (!wasFinisherReady && isFinisherReady) {
                    signalFinisherReady(tracker)
                }
            }
        }

    private val _finisherReady = MutableSharedFlow<Unit>()
    val finisherReady = _finisherReady.asSharedFlow()

    private fun signalFinisherReady(viewModel: TrackerViewModel) {
        viewModel.viewModelScope.launch {
            _finisherReady.emit(Unit)
        }
    }

    internal fun reset() {
        life = INIT_LIFE
        // Make sure force is taken from the pool
        force = 0
        force = INIT_FORCE
    }

    val isFinisherReady
        get() = _life.value <= _force.value

    internal var state: State
        get() = State(life, force)
        set(state) {
            _life.value = state.life
            _force.value = state.force
        }
}

class TrackerViewModel(preferences: AppPreferences) : ViewModel() {
    // https://stackoverflow.com/a/65259708
    class Factory(private val preferences: AppPreferences) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TrackerViewModel::class.java)) {
                return TrackerViewModel(preferences) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    internal data class State(val players: List<Player.State>, val forcePool: Int, val beat: Int, val isFinalBeat: Boolean, val victory: Victory?)

    internal var undoLevel = 0

    val players = arrayOf(Player(this), Player(this))

    private fun randomPlayer(): Player {
        return if (Random.nextBoolean()) players[0] else players[1]
    }

    private val _activePlayer = MutableStateFlow<Player?>(null)
    val activePlayerFlow = _activePlayer.asStateFlow()
    var activePlayer: Player?
        get() = _activePlayer.value
        set(value) {
            _activePlayer.value = value
        }

    init {
        if (preferences.getSetting(Settings.Tracker.randomActivePlayer)) {
            activePlayer = randomPlayer()
        }
    }

    private val _forcePool = MutableStateFlow(INIT_FORCE_POOL - INIT_FORCE * 2)
    val forcePoolFlow = _forcePool.asStateFlow()
    var forcePool: Int
        get() = forcePoolFlow.value
        set(value) {
            UndoableAction(this).use {
                _forcePool.value = value.coerceAtLeast(0)
                _isFinalBeat = forcePoolFlow.value == 0
            }
        }

    private val _beat = MutableStateFlow(1)
    val beatFlow = _beat.asStateFlow()
    var beat: Int
        get() = beatFlow.value
        private set(value) {
            UndoableAction(this).use {
                _beat.value = value
                viewModelScope.launch {
                    _beatStartEvent.emit(Unit)
                }
            }
        }

    private val _beatStartEvent = MutableSharedFlow<Unit>()
    val beatStartEvent = _beatStartEvent.asSharedFlow()

    private var _isFinalBeat = false
    private val _finalBeatEvent = MutableSharedFlow<Unit>()
    val finalBeatEvent = _finalBeatEvent.asSharedFlow()

    private val _victory = MutableStateFlow<Victory?>(null)
    val victoryFlow = _victory.asStateFlow()
    var victory: Victory?
        get() = _victory.value
        set(value) {
            _victory.value = value
        }


    private val _history = ArrayList<State>()

    internal fun pushState(state: State) {
        _history.add(state)
    }

    private fun popState() {
        assert(undoLevel == 0)
        state = _history.removeLastOrNull() ?: return
    }

    internal var state: State
        get() = State(players.map { it.state }, forcePool, beat, _isFinalBeat, victory)
        set(state) {
            for ((player, playerState) in players.zip(state.players)) {
                player.state = playerState
            }
            _forcePool.value = state.forcePool
            _beat.value = state.beat
            _isFinalBeat = state.isFinalBeat
            _victory.value = state.victory
        }

    fun nextBeat() {
        UndoableAction(this).use {
            if (_isFinalBeat) {
                victory = if (players[0].life > players[1].life) {
                    Victory(players[0], R.string.message_timeout_life)
                } else if (players[1].life > players[0].life) {
                    Victory(players[1], R.string.message_timeout_life)
                } else if (activePlayer == null) {
                    Victory(null, R.string.message_timeout_noreactive)
                } else {
                    val reactivePlayer = if (players[0] == activePlayer) players[1] else players[0]
                    Victory(reactivePlayer, R.string.message_timeout_reactive)
                }
            } else {
                for (player in players) {
                    val force = if (player.life < FORCE_BONUS_LIFE_THRESHOLD) 2 else 1
                    player.force += force
                }

                beat += 1

                if (_isFinalBeat) {
                    viewModelScope.launch {
                        _finalBeatEvent.emit(Unit)
                    }
                }
            }
            //Unit
        }
    }

    fun reset(randomActivePlayer: Boolean) {
        UndoableAction(this).use {
            _isFinalBeat = false

            forcePool = INIT_FORCE_POOL
            for (player in players) {
                player.reset()
            }

            beat = 1

            activePlayer = if (randomActivePlayer) randomPlayer() else null

            victory = null
        }
    }

    fun undo() {
        popState()
    }
}