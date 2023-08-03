package co.elcrow.battlecontainer.data

import co.elcrow.battlecontainer.R
import java.net.URL

enum class Product(val nameId: Int, val fullNameId: Int, val characters: Array<Character>, val storePage: URL? = null) {
    // NOTE: the enum variant names are used as lookup keys for collection data and should not be changed
    War(
        R.string.product_war,
        R.string.product_war_full,
        arrayOf(
            Character.Cadenza,
            Character.Cherri,
            Character.Demitras,
            Character.Heketch,
            Character.Hepzibah,
            Character.Hikaru,
            Character.Kallistar,
            Character.Kehrolyn,
            Character.Khadath,
            Character.Lixis,
            Character.Luc,
            Character.Magdelina,
            Character.Rukyuk,
            Character.Sagas,
            Character.Seth,
            Character.TatsumiJuto,
            Character.Vanaah,
            Character.Zaamassal
        ),
    ),
    Devastation(
        R.string.product_devastation,
        R.string.product_devastation_full,
        arrayOf(
            Character.Adjenna, Character.Alexian, Character.Arec, Character.Aria,
            Character.Byron, Character.Cesar, Character.Clinhyde, Character.Clive,
            Character.Eligor, Character.Endrbyt, Character.Gar, Character.Gaspar,
            Character.Gerard, Character.Iaxus, Character.Joal, Character.Kajia,
            Character.Kaitlyn, Character.Karin, Character.Kei, Character.Lesandra,
            Character.Lymn, Character.Malandrax, Character.Marmelee, Character.Mikhail,
            Character.Oriana, Character.Ottavia, Character.Pendros, Character.Rexan,
            Character.Runika, Character.Shekhtur, Character.Tanis, Character.Voco
        ),
        storePage = URL("https://level99games.com/products/battlecon-devastation")
    ),
    Fate(
        R.string.product_fate,
        R.string.product_fate_full,
        arrayOf(
            Character.Alumis,
            Character.Baenvier,
            Character.Eustace,
            Character.Iri,
            Character.Jager,
            Character.Larimore,
            Character.Sarafina,
            Character.Thessala,
            Character.Welsie,
            Character.Xenitia
        ),
    ),
    Trials(
        R.string.product_trials,
        R.string.product_trials_full,
        arrayOf(
            Character.Amon,
            Character.Burgundy,
            Character.Cindra,
            Character.Dareios,
            Character.Dravil,
            Character.Hayden,
            Character.Kimbhe,
            Character.Lucida,
            Character.Trias,
            Character.Wardlaw
        ),
        storePage = URL("https://www.level99store.com/products/battlecon-trials"),
    ),
    Wanderers(
        R.string.product_wanderers,
        R.string.product_wanderers_full,
        arrayOf(
            Character.Anya, Character.Cionaodh, Character.Dajiin, Character.Feylana, Character.John,
            Character.Kai, Character.Kora, Character.Rin, Character.Vekyl, Character.Wendy
        ),
    ),
    // Solo Fighters
    HimelNine(
        R.string.product_himelnine,
        R.string.product_himelnine_full,
        arrayOf(Character.HimelNine),
        storePage = URL("https://level99games.com/products/battlecon-himel-nine-solo-fighter"),
    ),
    Victor(
        R.string.product_victor,
        R.string.product_victor_full,
        arrayOf(Character.Victor),
        storePage = URL("https://level99games.com/products/battlecon-victor-solo-fighter"),
    ),
    OriaxTwo(
        R.string.product_oriaxtwo,
        R.string.product_oriaxtwo_full,
        arrayOf(Character.OriaxTwo),
        storePage = URL("https://level99games.com/products/battlecon-oriax-solo-fighter"),
    ),
    Takeshi(
        R.string.product_takeshi,
        R.string.product_takeshi_full,
        arrayOf(Character.Takeshi),
        storePage = URL("https://level99games.com/products/battlecon-takeshi-solo-fighter"),
    ),
    Lucius(
        R.string.product_lucius,
        R.string.product_lucius_full,
        arrayOf(Character.Lucius),
        storePage = URL("https://level99games.com/products/battlecon-lucius-solo-fighter"),
    ),
    Andrus(
        R.string.product_andrus,
        R.string.product_andrus_full,
        arrayOf(Character.Andrus),
        storePage = URL("https://level99games.com/products/battlecon-andrus-solo-fighter"),
    ),
    Rheye(
        R.string.product_rheye,
        R.string.product_rheye_full,
        arrayOf(Character.Rheye),
        storePage = URL("https://level99games.com/products/battlecon-rheye-solo-fighter"),
    ),
    Eliza(
        R.string.product_eliza,
        R.string.product_eliza_full,
        arrayOf(Character.Eliza),
        storePage = URL("https://level99games.com/products/battlecon-eliza-solo-fighter"),
    ),
    EvilHikaru(
        R.string.product_evilhikaru,
        R.string.product_evilhikaru_full,
        arrayOf(Character.EvilHikaru),
        storePage = URL("https://level99games.com/products/battlecon-evil-hikaru-solo-fighter"),
    ),
    Anath(
        R.string.product_anath,
        R.string.product_anath_full,
        arrayOf(Character.Anath),
        storePage = URL("https://level99games.com/products/battlecon-anath-solo-fighter"),
    ),
    ClausWyndhal(
        R.string.product_clauswyndhal,
        R.string.product_clauswyndhal_full,
        arrayOf(Character.Claus),
        storePage = URL("https://level99games.com/products/battlecon-claus-wyndhal-solo-fighter"),
    ),
    Dolores(
        R.string.product_dolores,
        R.string.product_dolores_full,
        arrayOf(Character.Dolores),
    ),
    Jin(
        R.string.product_jin,
        R.string.product_jin_full,
        arrayOf(Character.Jin),
    ),
    Merjoram(
        R.string.product_merjoram,
        R.string.product_merjoram_full,
        arrayOf(Character.Merjoram),
    ),
    RarittiSikhar(
        R.string.product_rarittisikhar,
        R.string.product_rarittisikhar_full,
        arrayOf(Character.Raritti),
    ),
    ;

    init {
        for (character in characters) {
            character_lookup[character] = this
        }
    }

    val isPromo
        get() = characters.size == 1

    companion object {
        fun lookup(character: Character) : Product {
            return character_lookup[character]!!
        }
    }
}

private val character_lookup: MutableMap<Character, Product> = mutableMapOf()