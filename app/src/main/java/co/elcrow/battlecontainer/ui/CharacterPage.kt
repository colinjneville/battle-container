package co.elcrow.battlecontainer.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.elcrow.battlecontainer.data.Character
import co.elcrow.battlecontainer.R
import co.elcrow.battlecontainer.character.Guide
import java.net.URL

@Composable
fun CharacterPage(appInfo: AppInfo, character: Character) {
    val filteredCharacters = appInfo.navController.previousBackStackEntry?.savedStateHandle?.get<List<Character>>("filteredCharacters") ?: listOf(character)
    CharacterPageInner(filteredCharacters, character)
}

@Preview
@Composable
fun CharacterPageInner(filteredCharacters: List<Character> = listOf(Character.Voco), character: Character = Character.Voco) {
    val context = LocalContext.current

    val cardVisibility = remember { MutableTransitionState(false).apply { targetState = true} }

    val characterIndex = filteredCharacters.indexOf(character)

    Column {
        Surface(color = MaterialTheme.colorScheme.primary, tonalElevation = 3.dp) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(context.getString(character.fullNameId), color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.displayMedium, textAlign = TextAlign.Center)
                Text(context.getString(character.taglineId), color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.displaySmall, textAlign = TextAlign.Center)
            }
        }
        LazyColumn() {
            item {
                Surface(color = Color.Black) {
                    CharacterCard(character, visibility = cardVisibility)
                }
            }
            item {
                Row {
                    val surfaceModifier = Modifier.padding(4.dp)
                    val textModifier = Modifier.padding(6.dp)

                    for (tag in arrayOf(
                        Pair(character.complexity.nameId, MaterialTheme.colorScheme.primaryContainer),
                        Pair(character.style.nameId, MaterialTheme.colorScheme.secondaryContainer),
                        Pair(character.product.nameId, MaterialTheme.colorScheme.tertiaryContainer))) {
                        val (labelId, color) = tag
                        Surface(shape = MaterialTheme.shapes.small,
                            tonalElevation = 3.dp,
                            color = color,
                            modifier = surfaceModifier) {
                            Text(context.getString(labelId), style = MaterialTheme.typography.labelLarge, modifier = textModifier)
                        }
                    }
                }
            }

            val faqUrl = character.faqUrl
            if (faqUrl != null) {
                item {
                    CharacterFaq(faqUrl)
                }
            }
            if (character.cotw != null) {
                item {
                    CharacterCotw(character.cotw)
                }
            }
            itemsIndexed(character.guides) {index, guide ->
                CharacterGuide(guide)
            }
        }
    }
}

@Composable
fun CharacterCard(character: Character, visibility: MutableTransitionState<Boolean>) {
    AnimatedVisibility(visibleState = visibility,
        enter = slideInHorizontally {
                it * 4 / 5
            } + fadeIn(
                initialAlpha = 0.2f
            ),
    ) {
        Image(painterResource(character.imageId),
              LocalContext.current.getString(character.fullNameId),
              contentScale = ContentScale.FillWidth,
              modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun CharacterFaq(url: URL) {
    val uriHandler = LocalUriHandler.current

    TextButton(onClick = { uriHandler.openUri(url.toString()) }) {
        Icon(painterResource(R.drawable.baseline_dataset_linked_24), null, modifier = Modifier.padding(6.dp, 0.dp))
        Text(LocalContext.current.getString(R.string.term_faq_errata))
    }
}

@Preview
@Composable
fun CharacterCotw(url: URL = URL("https://google.com/")) {
    val uriHandler = LocalUriHandler.current

    TextButton(onClick = { uriHandler.openUri(url.toString()) }) {
        Icon(painterResource(R.drawable.baseline_link_24), null, modifier = Modifier.padding(6.dp, 0.dp))
        Text(LocalContext.current.getString(R.string.term_cotw))
    }
}

@Preview
@Composable
fun CharacterGuide(guide: Guide = Guide("Voco for Dummies", "Voco", URL("https://google.com"))) {
    val uriHandler = LocalUriHandler.current

    TextButton(onClick = { uriHandler.openUri(guide.url.toString()) }) {
        Column {
            Row {
                Icon(painterResource(guide.type.iconId), null, modifier = Modifier.padding(6.dp, 0.dp))
                Text(guide.name)
            }
            Text(LocalContext.current.getString(R.string.misc_by) + " " + guide.author,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.align(Alignment.End))
        }

    }
}