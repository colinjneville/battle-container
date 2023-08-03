package co.elcrow.battlecontainer.character

import co.elcrow.battlecontainer.R

enum class Complexity(val nameId: Int, val descriptionId: Int) {
    Novice(R.string.character_complexity_novice, R.string.character_complexity_novice_desc),
    Intermediate(R.string.character_complexity_intermediate, R.string.character_complexity_intermediate_desc),
    Advanced(R.string.character_complexity_advanced, R.string.character_complexity_advanced_desc),
}