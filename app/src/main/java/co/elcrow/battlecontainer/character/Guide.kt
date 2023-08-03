package co.elcrow.battlecontainer.character

import androidx.annotation.DrawableRes
import co.elcrow.battlecontainer.R
import java.net.URL

enum class GuideType(@DrawableRes val iconId: Int) {
    Text(R.drawable.baseline_link_24),
    Video(R.drawable.baseline_ondemand_video_24),
}

data class Guide(val name: String, val author: String, val url: URL, val type: GuideType = GuideType.Text)
