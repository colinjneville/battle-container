package co.elcrow.battlecontainer.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.elcrow.battlecontainer.R
import co.elcrow.battlecontainer.data.Character
import kotlinx.coroutines.CoroutineScope

enum class AppPage(@StringRes val nameId: Int, val navPath: String, @DrawableRes val iconFilledId: Int, @DrawableRes val iconOutlineId: Int, val composable: @Composable (AppInfo) -> Unit, val topBarComposable: (@Composable (NavHostController) -> Unit)? = null) {
    Tracker(
        R.string.page_tracker, "tracker",
        R.drawable.baseline_style_24,
        R.drawable.outline_style_24, { TrackerPage(it) }),
    Characters(
        R.string.page_characters, "characters",
        R.drawable.baseline_people_24,
        R.drawable.outline_people_24, { CharactersPage(it) }),
    Rules(
        R.string.page_rulebook, "rulebook",
        R.drawable.baseline_picture_as_pdf_24,
        R.drawable.outline_picture_as_pdf_24, { RulebookPage(it) }),
    Collection(
        R.string.page_collection, "collection",
        R.drawable.baseline_checklist_24,
        R.drawable.outline_checklist_24, { CollectionPage(it) }),
    Settings(
        R.string.page_settings, "settings",
        R.drawable.baseline_settings_24,
        R.drawable.outline_settings_24, { SettingsPage(it) }),
}

@Preview
@Composable
fun App() {
    val navController = rememberNavController()

    val snackbarHostState = remember { SnackbarHostState() }

    val topBarTitle = remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = { AppNavigationBar(navController) },

        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        val appInfo = AppInfo(navController, scope, topBarTitle, snackbarHostState)
        NavHost(
            navController,
            startDestination = AppPage.values().first().navPath,
            modifier = Modifier.padding(innerPadding)
        ) {
            for (appPage in AppPage.values()) {
                composable(appPage.navPath, content = { appPage.composable(appInfo) })
            }
            composable("character/{characterId}",
                arguments = listOf(navArgument("characterId") { type = NavType.IntType })
            ) { backStackEntry ->
                val characterId = backStackEntry.arguments?.getInt("characterId")!!
                val character = Character.values()[characterId]
                appInfo.topBarTitle.value = ""
                CharacterPage(appInfo, character)
            }
            composable("triggers") { TriggersPage(appInfo) }
        }
    }
}

@Composable
fun AppNavigationBar(navController: NavHostController) {
    val context = LocalContext.current

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(windowInsets = WindowInsets.navigationBars) {
        for (appPage in AppPage.values()) {
            val selected =
                currentDestination?.hierarchy?.any { it.route == appPage.navPath } ?: false
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(if (selected) appPage.iconFilledId else appPage.iconOutlineId),
                        contentDescription = context.getString(appPage.nameId)
                    )
                },
                label = {
                    Text(context.getString(appPage.nameId))
                },
                selected = selected,
                onClick = {
                    navController.navigate(appPage.navPath) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

data class AppInfo(val navController: NavHostController, val appScope: CoroutineScope, val topBarTitle: MutableState<String>, val snackbarHostState: SnackbarHostState)