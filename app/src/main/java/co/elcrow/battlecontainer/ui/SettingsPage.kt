package co.elcrow.battlecontainer.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.elcrow.battlecontainer.AppPreferences
import co.elcrow.battlecontainer.BooleanSetting
import co.elcrow.battlecontainer.MutableAppPreferences
import co.elcrow.battlecontainer.R
import co.elcrow.battlecontainer.Settings
import co.elcrow.battlecontainer.dataStore
import co.elcrow.battlecontainer.getSetting
import co.elcrow.battlecontainer.toggleSetting
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

@Composable
fun SettingsPage(appInfo: AppInfo) {
    SettingsPageInner()
}

@Preview
@Composable
fun SettingsPageInner() {
    val context = LocalContext.current
    Column {
        Column(modifier = Modifier.weight(1f)) {
            Surface(color = MaterialTheme.colorScheme.primary, tonalElevation = 3.dp) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        context.getString(R.string.header_settings),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.displayMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
            LazyColumn {
                item {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            context.getString(R.string.header_settings_tracker),
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(16.dp, 8.dp),
                        )
                    }
                }
                item {
                    SettingBoolean(Settings.Tracker.randomActivePlayer)
                }
                item {
                    SettingBoolean(Settings.Tracker.confirmReset)
                }
            }
        }
        var uriHandler = LocalUriHandler.current
        Surface(
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            tonalElevation = 3.dp,
            modifier = Modifier.fillMaxWidth().clickable { uriHandler.openUri(context.getString(R.string.url_privacy_policy)) }) {
            Text(
                context.getString(R.string.misc_privacy_policy),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp, 8.dp),
            )
        }
    }
}

@Composable
fun SettingBoolean(setting: BooleanSetting) {
    val context = LocalContext.current

    val flow = remember {
        context.dataStore.data.map { preferences ->
            AppPreferences(preferences).getSetting(setting)
        }
    }.collectAsStateWithLifecycle(initialValue = setting.defaultValue)

    Surface(
        onClick = { runBlocking { context.dataStore.edit { settings -> MutableAppPreferences(settings).toggleSetting(setting) } } },
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = flow.value, onCheckedChange = null)
            Text(context.getString(setting.descId))
        }
    }
}
