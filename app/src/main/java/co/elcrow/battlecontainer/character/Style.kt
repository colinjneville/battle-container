package co.elcrow.battlecontainer.character

import co.elcrow.battlecontainer.R

enum class Style(val nameId: Int, val descriptionId: Int) {
    Slugger(R.string.character_style_slugger, R.string.character_style_slugger_desc),
    Tactician(R.string.character_style_tactician, R.string.character_style_tactician_desc),
    Mage(R.string.character_style_mage, R.string.character_style_mage_desc),
    Disrupter(R.string.character_style_disruptor, R.string.character_style_disruptor_desc),
    Brawler(R.string.character_style_brawler, R.string.character_style_brawler_desc),
    Tinkerer(R.string.character_style_tinkerer, R.string.character_style_tinkerer_desc),
}