package co.elcrow.battlecontainer.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.elcrow.battlecontainer.R

enum class Trigger(@StringRes val nameId: Int, @StringRes val descId: Int, val color: Color) {
    Reveal(R.string.trigger_reveal, R.string.trigger_reveal_desc, Color.Gray),
    Start(R.string.trigger_start, R.string.trigger_start_desc, Color.Green),
    Before(R.string.trigger_before, R.string.trigger_before_desc, Color.Blue),
    Hit(R.string.trigger_hit, R.string.trigger_hit_desc, Color.Red),
    Damage(R.string.trigger_damage, R.string.trigger_damage_desc, Color.Red),
    After(R.string.trigger_after, R.string.trigger_after_desc, Color.Blue),
    End(R.string.trigger_end, R.string.trigger_end_desc, Color.Green),
    Recycle(R.string.trigger_recycle, R.string.trigger_recycle_desc, Color.Gray),
}

@Composable
fun TriggersPage(appInfo: AppInfo) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp),
           modifier = Modifier.fillMaxSize().padding(50.dp, 20.dp)) {
        for (trigger in Trigger.values()) {
            TriggerBox(trigger, modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
fun TriggerBox(trigger: Trigger = Trigger.Reveal, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Surface(color = trigger.color, border = BorderStroke(2.dp, Color.Black), modifier = modifier) {
        Column(modifier = Modifier.padding(15.dp, 10.dp)) {
            Text(text = context.getString(trigger.nameId), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            Text(text = context.getString(trigger.descId), textAlign = TextAlign.Justify)
        }

    }
}