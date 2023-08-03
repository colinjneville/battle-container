package co.elcrow.battlecontainer.ui

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.elcrow.battlecontainer.data.Product
import co.elcrow.battlecontainer.R
import co.elcrow.battlecontainer.dataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

val Product.preferencesKey
    get() = booleanPreferencesKey("collection_$name")

suspend fun Product.toggleInCollection(context: Context) {
    context.dataStore.edit { settings ->
        val inCollection = settings[preferencesKey] ?: false
        settings[preferencesKey] = !inCollection
    }
}

@Composable
fun CollectionPage(appInfo: AppInfo, modifier: Modifier = Modifier) {
    CollectionPageInner(modifier)
}

@Preview
@Composable
fun CollectionPageInner(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column {
        Surface(color = MaterialTheme.colorScheme.primary, tonalElevation = 3.dp) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(context.getString(R.string.header_collection), color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.displayMedium, textAlign = TextAlign.Center)
            }
        }
        LazyColumn() {
            itemsIndexed(Product.values()) { index, item ->
                val color = if (index % 2 == 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant
                CollectionItem(item, color)
            }
        }
    }
}

@Composable
fun CollectionItem(product: Product, color: Color) {
    val context = LocalContext.current
    val inCollection = remember {
        context.dataStore.data
            .map { preferences ->
                preferences[product.preferencesKey] ?: false
            }
    }.collectAsStateWithLifecycle(initialValue = false)

    val headlineContent = @Composable {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = inCollection.value, onCheckedChange = null)
            Text(context.getString(product.fullNameId), )
        }
    }

    ListItem(headlineContent = headlineContent,
        colors = ListItemDefaults.colors(containerColor = color),
        modifier = Modifier.clickable { runBlocking { product.toggleInCollection(context) } })
}