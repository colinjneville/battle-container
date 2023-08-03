package co.elcrow.battlecontainer.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import co.elcrow.battlecontainer.character.Complexity
import co.elcrow.battlecontainer.character.Style
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.elcrow.battlecontainer.data.Character
import co.elcrow.battlecontainer.data.Product
import co.elcrow.battlecontainer.R

@Composable
fun CharactersPage(appInfo: AppInfo) {
    val onSelectCharacter: (List<Character>, Character) -> Unit = { filteredCharacters, character ->
        appInfo.navController.currentBackStackEntry?.savedStateHandle?.apply {
            set("filteredCharacters", filteredCharacters)
        }
        appInfo.navController.navigate("character/${character.ordinal}")
    }

    CharactersPageInner(onSelectCharacter)
}

@Preview
@Composable
fun CharactersPageInner(onSelectCharacter: (List<Character>, Character) -> Unit = { _, _ -> }, viewModel: CharactersViewModel = viewModel()) {
    val context = LocalContext.current

    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val inComplexity by viewModel.inComplexity.collectAsStateWithLifecycle()
    val inStyle by viewModel.inStyle.collectAsStateWithLifecycle()
    val inProduct by viewModel.inProduct.collectAsStateWithLifecycle()
    val inPromo by viewModel.inPromo.collectAsStateWithLifecycle()
    val inCollection by viewModel.inCollection.collectAsStateWithLifecycle()

    val filter = SearchFilter(searchText, inCollection, inComplexity, inStyle, inProduct, inPromo)
    val filteredCharacters = filter.apply(context, Character.values().asList())

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(tonalElevation = 3.dp) {
            Column {
                Search(searchText, onTextChange = { viewModel.setSearchText(it) }, onRandomCharacter = {
                    if (filteredCharacters.isNotEmpty()) {
                        onSelectCharacter(filteredCharacters, filteredCharacters.random())
                    }
                })

                Filters(inComplexity, viewModel::toggleComplexity,
                    inStyle, viewModel::toggleStyle,
                    inProduct, viewModel::toggleProduct,
                    inPromo, viewModel::togglePromo,
                    inCollection, viewModel::toggleInCollection,
                )
            }
        }

        Results(filteredCharacters, onSelectCharacter = { onSelectCharacter(filteredCharacters, it) })
    }
}

@Composable
fun ColumnScope.Search(text: String, onTextChange: (String) -> Unit, onRandomCharacter: () -> Unit, modifier: Modifier = Modifier) {
    SearchBar(
        text,
        onQueryChange = onTextChange,
        onActiveChange = { },
        trailingIcon = {
            Icon(painterResource(R.drawable.baseline_question_mark_24), "Random",
                 modifier = Modifier
                     .clip(CircleShape)
                     .clickable { onRandomCharacter() })
        },
        onSearch = { onRandomCharacter() },
        active = false,
        placeholder = { Text("Search", modifier = Modifier.padding(20.dp, 0.dp)) },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {

    }
}

@Composable
fun Filters(inComplexity: EnumSet<Complexity>, onInComplexityToggled: (Complexity) -> Unit,
            inStyle: EnumSet<Style>, onInStyleToggled: (Style) -> Unit,
            inProduct: EnumSet<Product>, onInProductToggled: (Product) -> Unit,
            inPromo: Boolean, onInPromoToggled: () -> Unit,
            inOwned: Boolean, onInOwnedToggled: () -> Unit) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    Surface(color = MaterialTheme.colorScheme.surfaceContainer, shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(20.dp, 5.dp)) {

        Column(modifier = Modifier.padding(20.dp, 5.dp)) {
            Row(modifier = Modifier.clickable { expanded = !expanded }) {
                val icon = if (expanded) R.drawable.baseline_remove_circle_24 else R.drawable.baseline_add_circle_24
                Icon(painterResource(id = icon), null, modifier = Modifier.align(Alignment.CenterVertically))
                Spacer(modifier = Modifier.weight(1f))
                Text(context.getString(R.string.button_character_filters), color = MaterialTheme.colorScheme.onPrimaryContainer, fontSize = 24.sp, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.weight(5f))
            }

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.animateContentSize()) {
                    Divider()
                    val modifier = Modifier.padding(0.dp, 5.dp);
                    CharactersComplexities(inComplexity, onInComplexityToggled, modifier = modifier)

                    CharactersStyles(inStyle, onInStyleToggled, modifier = modifier)

                    CharactersProducts(inProduct, onInProductToggled, inPromo, onInPromoToggled, modifier = modifier)

                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onInOwnedToggled() }) {
                        Checkbox(checked = inOwned, onCheckedChange = null)
                        Text(context.getString(R.string.button_owned_filter), style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }
}

@Composable
fun CharactersComplexities(selected: EnumSet<Complexity>, onSelectedChange: (Complexity) -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Complexity.values().forEach { complexity ->
            val complexitySelected = selected[complexity]
            CharactersComplexity(complexity, complexitySelected, onSelectedToggle = { onSelectedChange(complexity) }, modifier = Modifier
                .weight(1f)
                .padding(1.dp))
        }
    }
}

@Composable
fun CharactersComplexity(complexity: Complexity, selected: Boolean, onSelectedToggle: () -> Unit, modifier: Modifier = Modifier) {
    SearchFilterChip(complexity.nameId, selected, onSelectedToggle, MaterialTheme.colorScheme.primaryContainer, modifier)
}

@Composable
fun CharactersStyles(selected: EnumSet<Style>, onSelectedToggle: (Style) -> Unit, modifier: Modifier = Modifier) {
    FlowRow(maxItemsInEachRow = 3, modifier = modifier) {
        Style.values().forEach { style ->
            val styleSelected = selected[style]
            CharactersStyle(style, styleSelected, onSelectedToggle = { onSelectedToggle(style) }, modifier = Modifier
                .weight(1f)
                .padding(1.dp))
        }
    }
}

@Composable
fun CharactersStyle(style: Style, selected: Boolean, onSelectedToggle: () -> Unit, modifier: Modifier = Modifier) {
    SearchFilterChip(style.nameId, selected, onSelectedToggle, MaterialTheme.colorScheme.secondaryContainer, modifier)
}

@Composable
fun CharactersProducts(selected: EnumSet<Product>, onSelectedToggle: (Product) -> Unit, promoSelected: Boolean, onPromoSelectedToggle: () -> Unit, modifier: Modifier = Modifier) {
    FlowRow(horizontalArrangement = Arrangement.SpaceEvenly,
            maxItemsInEachRow = 3,
            modifier = modifier,
        ) {
        for (product in Product.values()) {
            if (!product.isPromo) {
                val productSelected = selected[product]

                CharactersProduct(
                    product,
                    productSelected,
                    onSelectedToggle = { onSelectedToggle(product) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(1.dp))
            }
        }
        // Promos as a single filter
        CharactersProduct(null, promoSelected, onSelectedToggle = {
                for (product in Product.values().filter { it.isPromo }) {
                    onPromoSelectedToggle()
                }
            },
            modifier = Modifier
                .weight(1f)
                .padding(1.dp))
    }
}

@Composable
fun CharactersProduct(product: Product?, selected: Boolean, onSelectedToggle: () -> Unit, modifier: Modifier = Modifier) {
    val labelId = product?.nameId ?: R.string.product_promo
    SearchFilterChip(labelId, selected, onSelectedToggle, MaterialTheme.colorScheme.tertiaryContainer, modifier)
}

@Composable
fun SearchFilterChip(labelId: Int, selected: Boolean, onSelectedToggle: () -> Unit, color: Color, modifier: Modifier = Modifier) {
    CompositionLocalProvider(
        LocalMinimumInteractiveComponentEnforcement provides false,
    ) {
        val colors = FilterChipDefaults.filterChipColors(selectedContainerColor = color)
        FilterChip(
            modifier = modifier,
            selected = selected,
            onClick = onSelectedToggle,
            label = { Text(LocalContext.current.getString(labelId), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) },
            colors = colors,
        )
    }
}

@Composable
fun Results(filteredCharacters: List<Character>, onSelectCharacter: (Character) -> Unit) {
    val context = LocalContext.current
    val characters = filteredCharacters.sortedBy { context.getString(it.nameId) }
    LazyColumn() {
        itemsIndexed(characters) { index, character ->
            val color = if (index % 2 == 0) ListItemDefaults.containerColor else MaterialTheme.colorScheme.surfaceVariant
            ListItem(headlineContent = { Text(context.getString(character.nameId), modifier = Modifier.padding(30.dp, 0.dp)) },
                     colors = ListItemDefaults.colors(containerColor = color),
                     modifier = Modifier.clickable { onSelectCharacter(character) }
            )
        }
    }
}