package co.elcrow.battlecontainer.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import co.elcrow.battlecontainer.AppPreferences
import co.elcrow.battlecontainer.R
import co.elcrow.battlecontainer.Settings
import co.elcrow.battlecontainer.getSetting
import co.elcrow.battlecontainer.preferencesBlocking
import co.elcrow.battlecontainer.ui.util.StrokedText
import co.elcrow.battlecontainer.ui.util.VerticalRotation
import co.elcrow.battlecontainer.ui.util.rotateVertically
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant

@Composable
fun TrackerPage(appInfo: AppInfo, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val preferences = context.preferencesBlocking()

    val viewModel: TrackerViewModel = viewModel(factory = TrackerViewModel.Factory(preferences))

    val snackbarMirroredHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.players[1].finisherReady.collect {
            snackbarMirroredHostState.showSnackbar(context.getString(R.string.snack_finisher_ready), duration = SnackbarDuration.Short)
        }
    }
    LaunchedEffect(Unit) {
        viewModel.players[0].finisherReady.collect {
            appInfo.snackbarHostState.showSnackbar(context.getString(R.string.snack_finisher_ready), duration = SnackbarDuration.Short)
        }
    }
    LaunchedEffect(Unit) {
        viewModel.finalBeatEvent.collect {
            launch {
                appInfo.snackbarHostState.showSnackbar(context.getString(R.string.snack_final_beat), duration = SnackbarDuration.Short)
            }
            snackbarMirroredHostState.showSnackbar(context.getString(R.string.snack_final_beat), duration = SnackbarDuration.Short)
        }
    }

    TrackerPageInner(modifier, viewModel)

    Box(contentAlignment = Alignment.TopCenter) {
        SnackbarHost(snackbarMirroredHostState, modifier = Modifier.rotate(180f)) {
            Snackbar(it, modifier = Modifier.align(Alignment.TopCenter))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackerPageInner(
    modifier: Modifier = Modifier,
    viewModel: TrackerViewModel = viewModel(factory = TrackerViewModel.Factory(AppPreferences(null)))) {
    val context = LocalContext.current

    var resetDialogOpen by remember { mutableStateOf(false) }

    var offset by remember { mutableFloatStateOf(0f) }
    val offsetMaxMagnitude = 384f

    val activePlayer by viewModel.activePlayerFlow.collectAsStateWithLifecycle()
    val victory by viewModel.victoryFlow.collectAsStateWithLifecycle()

    val beatStartFlow = remember {
        viewModel.beatStartEvent.map { Instant.now()!! }
    }

    val scrollState = rememberScrollableState { delta ->
        val prevOffset = offset
        offset = (offset + delta).coerceIn(-offsetMaxMagnitude, offsetMaxMagnitude)
        if (offset != prevOffset) {
            if (offset == offsetMaxMagnitude) {
                viewModel.activePlayer = viewModel.players[1]
            } else if (offset == -offsetMaxMagnitude) {
                viewModel.activePlayer = viewModel.players[0]
            }
        }
        offset - prevOffset
    }
    val overscroll = ScrollableDefaults.overscrollEffect()

    val resetFunc = {
        offset = 0f
        val preferences = context.preferencesBlocking()
        viewModel.reset(preferences.getSetting(Settings.Tracker.randomActivePlayer))
    }

    Column(
        modifier = Modifier
            .scrollable(scrollState, Orientation.Vertical, overscrollEffect = overscroll)
            .overscroll(overscroll)
    ) {
        PlayerTracker(viewModel.players[0],
            active = activePlayer?.equals(viewModel.players[0]),
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            victory = victory,
            modifier = Modifier
                .weight(1f)
                .rotate(180f))
        TrackerControlBar(viewModel.forcePoolFlow, viewModel.beatFlow, beatStartFlow, onUndoClicked = { viewModel.undo() }, onNextBeatClicked = { viewModel.nextBeat() },
            onResetClicked = {
                if (context.preferencesBlocking().getSetting(
                        Settings.Tracker.confirmReset
                    )) {
                    resetDialogOpen = true
                } else {
                    resetFunc()
                }
             },
            victory)
        PlayerTracker(viewModel.players[1],
            active = activePlayer?.equals(viewModel.players[1]),
            color = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            victory = victory,
            modifier = Modifier.weight(1f))
    }

    if (resetDialogOpen) {
        TrackerResetDialog(onClose = { resetDialogOpen = false }, onConfirmClicked = resetFunc)
    }
}


@Preview
@Composable
fun TrackerResetDialog(onClose: () -> Unit = {}, onConfirmClicked: () -> Unit = {}) {
    val context = LocalContext.current
    AlertDialog(
                title = { Text(context.getString(R.string.alert_tracker_reset_title)) },
                text = {
                    Text(context.getString(R.string.alert_tracker_reset_body))
                },
                confirmButton = {
                    TextButton(onClick = {
                        onClose()
                        onConfirmClicked()
                    }) {
                        Text(context.getString(R.string.alert_tracker_reset_confirm))
                    }
                },
                dismissButton = {
                    TextButton(onClick = onClose) {
                        Text(context.getString(R.string.alert_tracker_reset_cancel))
                    }
                },
                onDismissRequest = onClose,
    )
}

// Arrange 3 item in a row with the middle item always centered
// Not a robust implementation, but works for our case
@Composable
fun CenteredRow(left: @Composable () -> Unit, center: @Composable () -> Unit, right: @Composable () -> Unit, modifier: Modifier = Modifier) {
    val content = @Composable {
        left()
        center()
        right()
    }
    Layout(content = content, modifier = modifier) {measurables, constraints ->
        val measurableLeft = measurables[0]
        val measurableCenter = measurables[1]
        val measurableRight = measurables[2]
        val height = maxOf(
            measurableLeft.minIntrinsicHeight(constraints.maxWidth),
            measurableRight.minIntrinsicHeight(constraints.maxWidth))

        val centerConstraints = constraints.copy(minHeight = height, maxHeight = height)

        val placeableCenter = measurableCenter.measure(centerConstraints)
        val sideLen = (constraints.maxWidth - placeableCenter.width) / 2
        val sideConstraints = centerConstraints.copy(minWidth = sideLen, maxWidth = sideLen)
        val placeableLeft = measurableLeft.measure(sideConstraints)
        val placeableRight = measurableRight.measure(sideConstraints)

        layout(constraints.maxWidth, height) {
            placeableLeft.placeRelative(0, 0)
            placeableCenter.placeRelative(sideLen, 0)
            placeableRight.placeRelative(sideLen + placeableCenter.width, 0)
        }
    }
}

@Composable
fun TrackerControlBar(forcePoolFlow: StateFlow<Int>, beatFlow: StateFlow<Int>, beatStartFlow: Flow<Instant>, onUndoClicked: () -> Unit, onNextBeatClicked: () -> Unit, onResetClicked: () -> Unit, victory: Victory?, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val forcePool by forcePoolFlow.collectAsStateWithLifecycle()
    val beat by beatFlow.collectAsStateWithLifecycle()
    val beatStart by beatStartFlow.collectAsStateWithLifecycle(Instant.now()!!)

    val tick = flow {
        while (currentCoroutineContext().isActive) {
            delay(1000)
            emit(Instant.now()!!)
        }
    }

    val beatNow by tick.collectAsStateWithLifecycle(Instant.now()!!)

    Surface(shadowElevation = 3.dp, color = MaterialTheme.colorScheme.tertiaryContainer, contentColor = MaterialTheme.colorScheme.onTertiaryContainer, modifier = modifier) {

        val left = @Composable {
            Row {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
                    val colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.tertiary)
                    OutlinedButton(colors = colors, onClick = onResetClicked) {
                        Text(context.getString(R.string.button_tracker_reset))
                    }
                    OutlinedButton(colors = colors, onClick = onUndoClicked) {
                        Text(context.getString(R.string.button_tracker_undo))
                    }
                }
            }
        }
        val center = @Composable {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Image(
                    painterResource(R.drawable.force),
                    context.getString(R.string.term_force_pool),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxHeight()
                )
                StrokedText(forcePool.toString(), color = Color.White, style = MaterialTheme.typography.displayMedium)
            }
        }

        val right = @Composable {
            Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(context.getString(R.string.term_beat) + " " + beat.toString())
                    }
                    val elevatedColors = ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.tertiary,
                        disabledContainerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.12f),
                        disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.38f)
                    )
                    ElevatedButton(enabled = victory == null, colors = elevatedColors, onClick = onNextBeatClicked) {
                        Text(context.getString(R.string.button_tracker_nextbeat))
                    }
                }

                val duration = Duration.between(beatStart, beatNow).coerceAtLeast(Duration.ZERO)
                Text(String.format("%d:%02d%n", duration.toMinutes(), duration.seconds % 60),
                     overflow = TextOverflow.Clip,
                     maxLines = 1,
                     style = MaterialTheme.typography.titleSmall,
                     modifier = Modifier
                         .rotateVertically(VerticalRotation.CLOCKWISE)
                         .align(Alignment.CenterVertically))
            }
        }

        CenteredRow(left = left, center = center, right = right, modifier = Modifier
            .padding(6.dp)
            .height(IntrinsicSize.Min))
    }
}

@Composable
fun PlayerTracker(player: Player, color: Color, contentColor: Color, active: Boolean?, victory: Victory?, modifier: Modifier = Modifier) {
    val life by player.lifeFlow.collectAsStateWithLifecycle()
    val force by player.forceFlow.collectAsStateWithLifecycle()

    val fullLifeColor = Color.White
    val highLifeColor = Color(191, 142, 138)
    val lowLifeColor = Color(122, 55, 49)
    val fullForceColor = Color(50, 186, 52)
    val otherForceColor = Color(130, 209, 131)

    val lifeColor = when (player.life) {
        20 -> fullLifeColor
        in 8..19 -> highLifeColor
        else -> lowLifeColor
    }
    val forceColor = if (player.force == 10) fullForceColor else otherForceColor

    val context = LocalContext.current

    Box(modifier) {
        Column {
            Counter(life, onValueChange = { player.life = it }, color = color, contentColor = contentColor, textColor = lifeColor, iconId = R.drawable.life, modifier = Modifier
                .padding(4.dp)
                .weight(6f))
            Counter(force, onValueChange = { player.force = it }, color = color, contentColor = contentColor, textColor = forceColor, iconId = R.drawable.force, modifier = Modifier
                .padding(4.dp)
                .weight(3f))
            AnimatedVisibility(visible = active != null, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                val textId = if (active == true) R.string.term_active_player else R.string.term_reactive_player
                val surfaceColor = if (active == true) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.surfaceDim
                val contentColor = if (active == true) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onSurface

                val shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)

                Surface(
                    color = surfaceColor,
                    contentColor = contentColor,
                    shape = shape,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .weight(1f)
                        .fillMaxWidth(0.6f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(context.getString(textId), style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(top = 4.dp))
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = victory != null,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            AnimatedVisibility(
                visible = victory != null,
                enter = expandIn(),
                exit = shrinkOut(),
            ) {
                if (victory != null) {
                    Surface(
                        color = MaterialTheme.colorScheme.scrim.copy(alpha = 0.32f),
                        modifier = Modifier.clickable { /* block clicks */ },
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            val headerId = when (victory.player) {
                                player -> R.string.message_victory
                                null -> R.string.message_victory_reactive
                                else -> R.string.message_defeat
                            }

                            Surface(
                                color = MaterialTheme.colorScheme.surface,
                                contentColor = MaterialTheme.colorScheme.onSurface,
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.padding(12.dp),
                                ) {
                                    Text(context.getString(headerId))
                                    if (victory.explanationId != null) {
                                        Text("(" + context.getString(victory.explanationId) + ")")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Counter(value: Int, onValueChange: (Int) -> Unit, color: Color = MaterialTheme.colorScheme.primaryContainer, contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer, textColor: Color, @DrawableRes iconId: Int, modifier: Modifier = Modifier) {
    Surface(shape = MaterialTheme.shapes.extraLarge,
            color = color,
            contentColor = contentColor,
            modifier = modifier) {
        Row(modifier = Modifier.padding(4.dp)) {
            ElevatedButton(
                onClick = { onValueChange(value - 1) },
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .fillMaxSize()
            ) {
                Icon(painterResource(R.drawable.baseline_remove_circle_24), null)
            }
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(4f)
            ) {
                Image(painterResource(iconId), null)
                StrokedText(value.toString(), color = textColor, style = MaterialTheme.typography.displayLarge, textAlign = TextAlign.Companion.Center)
            }
            ElevatedButton(
                onClick = { onValueChange(value + 1) },
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .fillMaxSize()
            ) {
                Icon(painterResource(R.drawable.baseline_add_circle_24), null)
            }
        }
    }
}