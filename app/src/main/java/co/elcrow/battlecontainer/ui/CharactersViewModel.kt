package co.elcrow.battlecontainer.ui

import android.content.Context
import androidx.annotation.CheckResult
import androidx.compose.runtime.Immutable
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.elcrow.battlecontainer.data.Character
import co.elcrow.battlecontainer.data.Product
import co.elcrow.battlecontainer.character.Complexity
import co.elcrow.battlecontainer.character.Style
import co.elcrow.battlecontainer.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking

@Immutable
class EnumSet<E: Enum<E>>(private val _backing: java.util.EnumSet<E>, private val _maxSize: Int) {
    companion object {
        inline fun<reified E: Enum<E>> noneOf(): EnumSet<E> {
            return EnumSet(java.util.EnumSet.noneOf(E::class.java), enumValues<E>().size)
        }

        inline fun<reified E: Enum<E>> allOf(): EnumSet<E> {
            return EnumSet(java.util.EnumSet.allOf(E::class.java), enumValues<E>().size)
        }
    }

    private fun copy(): EnumSet<E> {
        return EnumSet(java.util.EnumSet.copyOf(_backing), _maxSize)
    }

    @CheckResult
    fun isFull(): Boolean {
        return _backing.size == _maxSize
    }

    @CheckResult
    fun isEmpty(): Boolean {
        return _backing.isEmpty()
    }

    @CheckResult
    fun toggled(e: E): EnumSet<E> {
        return copy().apply {
            if (!_backing.remove(e)) {
                _backing.add(e)
            }
        }
    }

    @CheckResult
    fun added(e: E): EnumSet<E> {
        return copy().apply {
            _backing.add(e)
        }
    }

    @CheckResult
    fun removed(e: E): EnumSet<E> {
        return copy().apply {
            _backing.remove(e)
        }
    }

    @CheckResult
    fun contains(e: E): Boolean {
        return _backing.contains(e)
    }

    operator fun get(e: E): Boolean {
        return contains(e)
    }
}

fun <T1, T2, T3, T4, T5, T6, R> combine(
    flow: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    flow4: Flow<T4>,
    flow5: Flow<T5>,
    flow6: Flow<T6>,
    transform: suspend (T1, T2, T3, T4, T5, T6) -> R
): Flow<R> = combine(
    combine(flow, flow2, flow3, ::Triple),
    combine(flow4, flow5, flow6, ::Triple)
) { t1, t2 ->
    transform(
        t1.first,
        t1.second,
        t1.third,
        t2.first,
        t2.second,
        t2.third
    )
}

data class SearchFilter(
    val searchText: String = "",
    val inCollection: Boolean = true,
    val inComplexity: EnumSet<Complexity> = EnumSet.allOf(),
    val inStyle: EnumSet<Style> = EnumSet.allOf(),
    val inProduct: EnumSet<Product> = EnumSet.allOf(),
    val inPromo: Boolean = true) {

    fun apply(context: Context, characters: List<Character>) : List<Character> {
        val preferences = runBlocking {
            context.dataStore.data.first()
        }
        return characters.filter { apply(context, it, preferences) }
    }

    fun apply(context: Context, character: Character, preferences: Preferences? = null) : Boolean {
        val preferences = preferences ?: runBlocking {
            context.dataStore.data.first()
        }
        return context.getString(character.fullNameId).contains(searchText, true)
                && inComplexity[character.complexity]
                && inStyle[character.style]
                && (character.isPromo || inProduct[character.product])
                && (!character.isPromo || inPromo)
                && (!inCollection || preferences[character.product.preferencesKey] ?: false)
    }
}

class CharactersViewModel() : ViewModel() {
    private val _searchText = MutableStateFlow("")
    var searchText: StateFlow<String> = _searchText.asStateFlow()

    fun setSearchText(value: String) {
        _searchText.value = value
    }

    private val _inCollection = MutableStateFlow(false)
    val inCollection: StateFlow<Boolean> = _inCollection.asStateFlow()

    private val _inComplexity = MutableStateFlow(EnumSet.allOf<Complexity>())
    val inComplexity: StateFlow<EnumSet<Complexity>> = _inComplexity.asStateFlow()

    private val _inStyle = MutableStateFlow(EnumSet.allOf<Style>())
    val inStyle: StateFlow<EnumSet<Style>> = _inStyle.asStateFlow()

    private val _inProduct = MutableStateFlow(EnumSet.allOf<Product>())
    val inProduct: StateFlow<EnumSet<Product>> = _inProduct.asStateFlow()

    private val _inPromo = MutableStateFlow(true)
    val inPromo: StateFlow<Boolean> = _inPromo.asStateFlow()

    val filter: StateFlow<SearchFilter> =
        combine(_searchText, _inCollection, _inComplexity, _inStyle, _inProduct, _inPromo) { searchText, inCollection, inComplexity, inStyle, inProduct, inPromo ->
            SearchFilter(searchText, inCollection, inComplexity, inStyle, inProduct, inPromo)
        }.stateIn(viewModelScope, SharingStarted.Lazily, SearchFilter())

    fun toggleInCollection() {
        _inCollection.value = !_inCollection.value
    }

    fun toggleComplexity(complexity: Complexity) {
        _inComplexity.value = _inComplexity.value.toggled(complexity)
    }

    fun toggleStyle(style: Style) {
        _inStyle.value = _inStyle.value.toggled(style)
    }

    fun toggleProduct(product: Product) {
        _inProduct.value = _inProduct.value.toggled(product)
    }

    fun togglePromo() {
        _inPromo.value = !_inPromo.value
    }
}