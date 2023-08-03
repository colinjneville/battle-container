package co.elcrow.battlecontainer.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import co.elcrow.battlecontainer.R
import co.elcrow.battlecontainer.character.Complexity
import co.elcrow.battlecontainer.character.Guide
import co.elcrow.battlecontainer.character.GuideType
import co.elcrow.battlecontainer.character.Style
import java.net.URL

enum class Character(
    @StringRes val nameId: Int,
    @StringRes val fullNameId: Int,
    @StringRes val taglineId: Int,
    val complexity: Complexity,
    val style: Style,
    @DrawableRes val imageId: Int,
    val faqHeading: String?,
    val cotw: URL? = null,
    val guides: Array<Guide> = emptyArray(),
    val faqs: Array<URL> = emptyArray()) {

    // War
    Cadenza(
        R.string.character_cadenza,
        R.string.character_cadenza_full,
        R.string.character_cadenza_tagline,
        Complexity.Novice,
        Style.Slugger,
        R.drawable.character_cadenza_card,
        "8k4k4co5ot1i",
    ),
    Cherri(
        R.string.character_cherri,
        R.string.character_cherri_full,
        R.string.character_cherri_tagline,
        Complexity.Advanced,
        Style.Disrupter,
        R.drawable.character_cherri_card,
        "hby24qu8zxv0",
    ),
    Demitras(
        R.string.character_demitras,
        R.string.character_demitras_full,
        R.string.character_demitras_tagline,
        Complexity.Novice,
        Style.Disrupter,
        R.drawable.character_demitras_card,
        "muexx53xcsf9",
    ),
    Heketch(
        R.string.character_heketch,
        R.string.character_heketch_full,
        R.string.character_heketch_tagline,
        Complexity.Intermediate,
        Style.Disrupter,
        R.drawable.character_heketch_card,
        "3zugrhka0q60",
    ),
    Hepzibah(
        R.string.character_hepzibah,
        R.string.character_hepzibah_full,
        R.string.character_hepzibah_tagline,
        Complexity.Intermediate,
        Style.Tinkerer,
        R.drawable.character_hepzibah_card,
        "xqmd0mcxckxj",
    ),
    Hikaru(
        R.string.character_hikaru,
        R.string.character_hikaru_full,
        R.string.character_hikaru_tagline,
        Complexity.Novice,
        Style.Brawler,
        R.drawable.character_hikaru_card,
        "7cxpvdrnhetc",
    ),
    Kallistar(
        R.string.character_kallistar,
        R.string.character_kallistar_full,
        R.string.character_kallistar_tagline,
        Complexity.Novice,
        Style.Mage,
        R.drawable.character_kallistar_card,
        "b6tf0bx4svz7",
    ),
    Kehrolyn(
        R.string.character_kehrolyn,
        R.string.character_kehrolyn_full,
        R.string.character_kehrolyn_tagline,
        Complexity.Novice,
        Style.Brawler,
        R.drawable.character_kehrolyn_card,
        "fr5d8l8w2y92",
    ),
    Khadath(
        R.string.character_khadath,
        R.string.character_khadath_full,
        R.string.character_khadath_tagline,
        Complexity.Intermediate,
        Style.Tactician,
        R.drawable.character_khadath_card,
        "bvs20d8grprp"
    ),
    Lixis(
        R.string.character_lixis,
        R.string.character_lixis_full,
        R.string.character_lixis_tagline,
        Complexity.Intermediate,
        Style.Disrupter,
        R.drawable.character_lixis_card,
        "25bjftryrxbi",
    ),
    Luc(
        R.string.character_luc,
        R.string.character_luc_full,
        R.string.character_luc_tagline,
        Complexity.Intermediate,
        Style.Disrupter,
        R.drawable.character_luc_card,
        "sipu5idnjkfn",
    ),
    Magdelina(
        R.string.character_magdelina,
        R.string.character_magdelina_full,
        R.string.character_magdelina_tagline,
        Complexity.Intermediate,
        Style.Mage,
        R.drawable.character_magdelina_card,
        "m1t39ntn1ldo",
        guides = arrayOf(
            Guide("Magdelina Discussion with Ashtaroth and Moon Knight", "Kevin Lambert", URL("https://www.youtube.com/watch?v=hfHNSGCdwhM"), GuideType.Video),
        )
    ),
    Rukyuk(
        R.string.character_rukyuk,
        R.string.character_rukyuk_full,
        R.string.character_rukyuk_tagline,
        Complexity.Advanced,
        Style.Brawler,
        R.drawable.character_rukyuk_card,
        "1orxkwolr1vi",
        guides = arrayOf(
            Guide("Rukyuk Discussion with MiX and Moon Knight", "Kevin Lambert", URL("https://www.youtube.com/watch?v=MDmqqemLcPA"), GuideType.Video),
        )
    ),
    Sagas(
        R.string.character_sagas,
        R.string.character_sagas_full,
        R.string.character_sagas_tagline,
        Complexity.Advanced,
        Style.Brawler,
        R.drawable.character_sagas_card,
        "edq0d4dorbze",
    ),
    Seth(
        R.string.character_seth,
        R.string.character_seth_full,
        R.string.character_seth_tagline,
        Complexity.Advanced,
        Style.Mage,
        R.drawable.character_seth_card,
        "ia8cdlcfwy3h",
    ),
    TatsumiJuto(
        R.string.character_tatsumijuto,
        R.string.character_tatsumijuto_full,
        R.string.character_tatsumijuto_tagline,
        Complexity.Advanced,
        Style.Tactician,
        R.drawable.character_tatsumijuto_card,
        "qz98qeizwcy4",
    ),
    Vanaah(
        R.string.character_vanaah,
        R.string.character_vanaah_full,
        R.string.character_vanaah_tagline,
        Complexity.Novice,
        Style.Brawler,
        R.drawable.character_vanaah_card,
        "35iznn8rxg5c",
    ),
    Zaamassal(
        R.string.character_zaamassal,
        R.string.character_zaamassal_full,
        R.string.character_zaamassal_tagline,
        Complexity.Advanced,
        Style.Tinkerer,
        R.drawable.character_zaamassal_card,
        "jvmfzabc6f5l",
    ),

    // Devastation
    Adjenna(
        R.string.character_adjenna,
        R.string.character_adjenna_full,
        R.string.character_adjenna_tagline,
        Complexity.Intermediate,
        Style.Mage,

        R.drawable.character_adjenna_card,
        "mfyva8da06u0",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/shajfe/character_of_the_week_adjenna_callista/"),
    ),
    Alexian(
        R.string.character_alexian,
        R.string.character_alexian_full,
        R.string.character_alexian_tagline,
        Complexity.Novice,
        Style.Slugger,

        R.drawable.character_alexian_card,
        "ghbx1jtoon84",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/sbuzay/character_of_the_week_king_alexian_xxxvii/"),
    ),
    Arec(
        R.string.character_arec,
        R.string.character_arec_full,
        R.string.character_arec_tagline,
        Complexity.Advanced,
        Style.Disrupter,

        R.drawable.character_arec_card,
        "bx70k2b93pef",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/s6bs5r/character_of_the_week_arec_russel_zane/"),
    ),
    Aria(
        R.string.character_aria,
        R.string.character_aria_full,
        R.string.character_aria_tagline,
        Complexity.Intermediate,
        Style.Disrupter,

        R.drawable.character_aria_card,
        "apdbj2sddh28",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/s0szfl/character_of_the_week_aria/"),
    ),
    Byron(
        R.string.character_byron,
        R.string.character_byron_full,
        R.string.character_byron_tagline,
        Complexity.Intermediate,
        Style.Mage,

        R.drawable.character_byron_card,
        "5159d53hu62f",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/rvaima/character_of_the_week_byron_krane/"),
        guides = arrayOf(
            Guide("Byron Discussion with MiX and Moon Knight", "Kevin Lambert", URL("https://www.youtube.com/watch?v=b9OYYrQFgH"), GuideType.Video),
        )
    ),
    Cesar(
        R.string.character_cesar,
        R.string.character_cesar_full,
        R.string.character_cesar_tagline,
        Complexity.Novice,
        Style.Slugger,

        R.drawable.character_cesar_card,
        "ox84os16gxo7",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/rpvvui/character_of_the_week_cesar_grist/"),
    ),
    Clinhyde(
        R.string.character_clinhyde,
        R.string.character_clinhyde_full,
        R.string.character_clinhyde_tagline,
        Complexity.Intermediate,
        Style.Brawler,

        R.drawable.character_clinhyde_card,
        "qy6btrmiyco5",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/rkvqh5/character_of_the_week_clinhyde_eight/"),
    ),
    Clive(
        R.string.character_clive,
        R.string.character_clive_full,
        R.string.character_clive_tagline,
        Complexity.Advanced,
        Style.Tinkerer,

        R.drawable.character_clive_card,
        "yzhaqno6r9c",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/rfpzh5/character_of_the_week_clive_melmont/"),
    ),
    Eligor(
        R.string.character_eligor,
        R.string.character_eligor_full,
        R.string.character_eligor_tagline,
        Complexity.Novice,
        Style.Slugger,

        R.drawable.character_eligor_card,
        "vbwuyvnm3b1p",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/rag7bv/character_of_the_week_eligor_larington/"),
    ),
    Endrbyt(
        R.string.character_endrbyt,
        R.string.character_endrbyt_full,
        R.string.character_endrbyt_tagline,
        Complexity.Advanced,
        Style.Tinkerer,

        R.drawable.character_endrbyt_card,
        "oq0qcd7l65gs",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/r57ag5/character_of_the_week_endrbyt/"),
    ),
    Gar(
        R.string.character_gar,
        R.string.character_gar_full,
        R.string.character_gar_tagline,
        Complexity.Novice,
        Style.Brawler,

        R.drawable.character_gar_card,
        "5ygb7j9j3xqa",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/k8m2ps/character_of_the_week_gar/"),
        guides = arrayOf(
            Guide("Gar Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/u1i7v9/gar_fighter_guide/")),
        )
    ),
    Gaspar(
        R.string.character_gaspar,
        R.string.character_gaspar_full,
        R.string.character_gaspar_tagline,
        Complexity.Advanced,
        Style.Brawler,

        R.drawable.character_gaspar_card,
        "8e0si7994s6a",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/qzx3pb/character_of_the_week_gaspar_geddon/"),
        guides = arrayOf(
            Guide("Gaspar Geddon Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/n6ajpi/gaspar_geddon_fighter_guide/")),
        )
    ),
    Gerard(
        R.string.character_gerard,
        R.string.character_gerard_full,
        R.string.character_gerard_tagline,
        Complexity.Advanced,
        Style.Tinkerer,

        R.drawable.character_gerard_card,
        "59bumup3med6",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/qunnn8/character_of_the_week_gerard_matranga/"),
    ),
    Iaxus(
        R.string.character_iaxus,
        R.string.character_iaxus_full,
        R.string.character_iaxus_tagline,
        Complexity.Advanced,
        Style.Slugger,

        R.drawable.character_iaxus_card,
        "orj38w1rbmay",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/qpj65m/character_of_the_week_iaxus_the_shattered/"),
    ),
    Joal(
        R.string.character_joal,
        R.string.character_joal_full,
        R.string.character_joal_tagline,
        Complexity.Intermediate,
        Style.Tinkerer,

        R.drawable.character_joal_card,
        "ksx6cdh1sf95",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/qknosr/character_of_the_week_joal_kalmor/"),
    ),
    Kajia(
        R.string.character_kajia,
        R.string.character_kajia_full,
        R.string.character_kajia_tagline,
        Complexity.Novice,
        Style.Brawler,

        R.drawable.character_kajia_card,
        "yiklbej67uv0",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/ps4vln/character_of_the_week_kajia_septie_salix/"),
    ),
    Kaitlyn(
        R.string.character_kaitlyn,
        R.string.character_kaitlyn_full,
        R.string.character_kaitlyn_tagline,
        Complexity.Advanced,
        Style.Tactician,

        R.drawable.character_kaitlyn_card,
        "ygpafahfi5py",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/pwmnyr/character_of_the_week_kaitlyn_van_sorrel/"),
    ),
    Karin(
        R.string.character_karin,
        R.string.character_karin_full,
        R.string.character_karin_tagline,
        Complexity.Intermediate,
        Style.Tactician,

        R.drawable.character_karin_card,
        "ujvpvphgthec",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/pltq3m/character_of_the_week_karin_brandtford/"),
    ),
    Kei(
        R.string.character_kei,
        R.string.character_kei_full,
        R.string.character_kei_tagline,
        Complexity.Intermediate,
        Style.Mage,

        R.drawable.character_kei_card,
        "e33swno3dw2f",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/kd3wvo/character_of_the_week_neuromille_kei/"),
    ),
    Lesandra(
        R.string.character_lesandra,
        R.string.character_lesandra_full,
        R.string.character_lesandra_tagline,
        Complexity.Advanced,
        Style.Tinkerer,

        R.drawable.character_lesandra_card,
        "k0owok9yaz7m",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/pepvsj/character_of_the_week_lesandra_machan/"),
    ),
    Lymn(
        R.string.character_lymn,
        R.string.character_lymn_full,
        R.string.character_lymn_tagline,
        Complexity.Advanced,
        Style.Mage,

        R.drawable.character_lymn_card,
        "kal2bxa21xrc",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/pa57s3/character_of_the_week_lymn/"),
    ),
    Malandrax(
        R.string.character_malandrax,
        R.string.character_malandrax_full,
        R.string.character_malandrax_tagline,
        Complexity.Advanced,
        Style.Disrupter,

        R.drawable.character_malandrax_card,
        "ei941vbjmnle",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/p5k7fz/character_of_the_week_malandrax_mecchi/"),
    ),
    Marmelee(
        R.string.character_marmelee,
        R.string.character_marmelee_full,
        R.string.character_marmelee_tagline,
        Complexity.Novice,
        Style.Mage,

        R.drawable.character_marmelee_card,
        "pioqhip39wkq",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/p15jlj/character_of_the_week_marmelee_greyheart/"),
    ),
    Mikhail(
        R.string.character_mikhail,
        R.string.character_mikhail_full,
        R.string.character_mikhail_tagline,
        Complexity.Novice,
        Style.Slugger,

        R.drawable.character_mikhail_card,
        "kalcaazcmh73",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/owi56q/character_of_the_week_mikhail_isen/"),
    ),
    Oriana(
        R.string.character_oriana,
        R.string.character_oriana_full,
        R.string.character_oriana_tagline,
        Complexity.Intermediate,
        Style.Mage,

        R.drawable.character_oriana_card,
        "w4b9lk64l9l6",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/os5i1z/character_of_the_week_orianavellopholetta/"),
        guides = arrayOf(
            Guide("Oriana Xenia Vellopholetta: A V4 Battlecon Guide", "migohunter", URL("https://www.reddit.com/r/Battlecon/comments/xmhk1y/oriana_xenia_vellopholetta_a_v4_battlecon_guide/")),
        )
    ),
    Ottavia(
        R.string.character_ottavia,
        R.string.character_ottavia_full,
        R.string.character_ottavia_tagline,
        Complexity.Intermediate,
        Style.Brawler,

        R.drawable.character_ottavia_card,
        "9xwio7wxdhs4",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/onkpi7/character_of_the_week_ottavia_six/"),
    ),
    Pendros(
        R.string.character_pendros,
        R.string.character_pendros_full,
        R.string.character_pendros_tagline,
        Complexity.Advanced,
        Style.Tactician,

        R.drawable.character_pendros_card,
        "ccnccnc2drs6",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/oixxfk/character_of_the_week_pendros_schalla/"),
    ),
    Rexan(
        R.string.character_rexan,
        R.string.character_rexan_full,
        R.string.character_rexan_tagline,
        Complexity.Intermediate,
        Style.Mage,

        R.drawable.character_rexan_card,
        "jgel10qex4bo",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/oedv97/character_of_the_week_cairngort_rexan/"),
    ),
    Runika(
        R.string.character_runika,
        R.string.character_runika_full,
        R.string.character_runika_tagline,
        Complexity.Advanced,
        Style.Tinkerer,

        R.drawable.character_runika_card,
        "9cu1iamb22ib",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/o9qx3q/character_of_the_week_runika_zenanen/"),
    ),
    Shekhtur(
        R.string.character_shekhtur,
        R.string.character_shekhtur_full,
        R.string.character_shekhtur_tagline,
        Complexity.Novice,
        Style.Disrupter,

        R.drawable.character_shekhtur_card,
        "egzrv3g9v07",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/o52n03/character_of_the_week_shekhtur_lenmorre/"),
    ),
    Tanis(
        R.string.character_tanis,
        R.string.character_tanis_full,
        R.string.character_tanis_tagline,
        Complexity.Advanced,
        Style.Tactician,

        R.drawable.character_tanis_card,
        "ilgm65sxy5g8",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/nzu5rs/character_of_the_week_tanis_trilives/"),
    ),
    Voco(
        R.string.character_voco,
        R.string.character_voco_full,
        R.string.character_voco_tagline,
        Complexity.Advanced,
        Style.Tactician,

        R.drawable.character_voco_card,
        "dwj9wcddrd9c",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/nvgrfo/character_of_the_week_voco_astrum/"),
        guides = arrayOf(
            Guide("Voco Astrum Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/u82q2x/voco_astrum_fighter_guide/")),
        )
    ),

    // Fate
    Alumis(
        R.string.character_alumis,
        R.string.character_alumis_full,
        R.string.character_alumis_tagline,
        Complexity.Intermediate,
        Style.Tactician,
        R.drawable.character_alumis_card,
        "vqdlomr9o42a",
    ),
    Baenvier(
        R.string.character_baenvier,
        R.string.character_baenvier_full,
        R.string.character_baenvier_tagline,
        Complexity.Novice,
        Style.Brawler,
        R.drawable.character_baenvier_card,
        "sxwqp98ia3c1",
    ),
    Eustace(
        R.string.character_eustace,
        R.string.character_eustace_full,
        R.string.character_eustace_tagline,
        Complexity.Novice,
        Style.Slugger,
        R.drawable.character_eustace_card,
        "u50m6wzn0j6",
    ),
    Iri(
        R.string.character_iri,
        R.string.character_iri_full,
        R.string.character_iri_tagline,
        Complexity.Advanced,
        Style.Mage,
        R.drawable.character_iri_card,
        "1jj5utakg99v",
    ),
    Jager(
        R.string.character_jager,
        R.string.character_jager_full,
        R.string.character_jager_tagline,
        Complexity.Novice,
        Style.Disrupter,
        R.drawable.character_jager_card,
        "c7kwp9dll78x",
    ),
    Larimore(
        R.string.character_larimore,
        R.string.character_larimore_full,
        R.string.character_larimore_tagline,
        Complexity.Novice,
        Style.Mage,
        R.drawable.character_larimore_card,
        "4zv3cxs4kwyp",
    ),
    Sarafina(
        R.string.character_sarafina,
        R.string.character_sarafina_full,
        R.string.character_sarafina_tagline,
        Complexity.Intermediate,
        Style.Tactician,
        R.drawable.character_sarafina_card,
        "fgegixic3nkw",
    ),
    Thessala(
        R.string.character_thessala,
        R.string.character_thessala_full,
        R.string.character_thessala_tagline,
        Complexity.Advanced,
        Style.Tinkerer,
        R.drawable.character_thessala_card,
        "n7lzox8etahi",

        guides = arrayOf(
            Guide("Thessala Discussion with MiX and Moon Knight", "Kevin Lambert", URL("https://www.youtube.com/watch?v=iVNHIOeGO3o"), GuideType.Video),
        )
    ),
    Welsie(
        R.string.character_welsie,
        R.string.character_welsie_full,
        R.string.character_welsie_tagline,
        Complexity.Advanced,
        Style.Disrupter,
        R.drawable.character_welsie_card,
        "dyd7r7w4910",
    ),
    Xenitia(
        R.string.character_xenitia,
        R.string.character_xenitia_full,
        R.string.character_xenitia_tagline,
        Complexity.Intermediate,
        Style.Mage,
        R.drawable.character_xenitia_card,
        "357q8bzfeky2",
    ),

    // Trials
    Amon(
        R.string.character_amon,
        R.string.character_amon_full,
        R.string.character_amon_tagline,
        Complexity.Intermediate,
        Style.Slugger,

        R.drawable.character_amon_card,
        "16z08t8hry91",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/uhlxkn/character_of_the_week_amon_elcela/"),
    ),
    Burgundy(
        R.string.character_burgundy,
        R.string.character_burgundy_full,
        R.string.character_burgundy_tagline,
        Complexity.Novice,
        Style.Tactician,

        R.drawable.character_burgundy_card,
        "w4g01sv9647x",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/uch8zb/character_of_the_week_burgundy_xii/"),
        guides = arrayOf(
            Guide("Burgundy Twelve Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/u3ltg6/burgundy_twelve_fighter_guide/")),
        )
    ),
    Cindra(
        R.string.character_cindra,
        R.string.character_cindra_full,
        R.string.character_cindra_tagline,
        Complexity.Novice,
        Style.Brawler,

        R.drawable.character_cindra_card,
        "k8jy1w49w1c3",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/u79wk5/character_of_the_week_cindra_flama/"),
    ),
    Dareios(
        R.string.character_dareios,
        R.string.character_dareios_full,
        R.string.character_dareios_tagline,
        Complexity.Advanced,
        Style.Tinkerer,

        R.drawable.character_dareios_card,
        "bwfr9hs9ghg1",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/u258io/character_of_the_week_darieos_kuel/"),
        guides = arrayOf(
            Guide("Dareios Kuel Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/mha5zb/dareios_kuel_fighter_guide/")),
        )
    ),
    Dravil(
        R.string.character_dravil,
        R.string.character_dravil_full,
        R.string.character_dravil_tagline,
        Complexity.Advanced,
        Style.Tactician,

        R.drawable.character_dravil_card,
        "v25nk2ec44e8",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/tx29e8/character_of_the_week_dravil_coldwater/"),
    ),
    Hayden(
        R.string.character_hayden,
        R.string.character_hayden_full,
        R.string.character_hayden_tagline,
        Complexity.Advanced,
        Style.Mage,

        R.drawable.character_hayden_card,
        "dujapu5ph38z",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/trenxf/character_of_the_week_hayden_morgan/"),
        guides = arrayOf(
            Guide("Hayden Morgan Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/ue2ns0/hayden_morgan_fighter_guide/")),
        )
    ),
    Kimbhe(
        R.string.character_kimbhe,
        R.string.character_kimbhe_full,
        R.string.character_kimbhe_tagline,
        Complexity.Novice,
        Style.Brawler,

        R.drawable.character_kimbhe_card,
        "hw9w2vemztst",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/sxzwp2/character_of_the_week_uleyle_kimbhe/"),
    ),
    Lucida(
        R.string.character_lucida,
        R.string.character_lucida_full,
        R.string.character_lucida_tagline,
        Complexity.Intermediate,
        Style.Disrupter,

        R.drawable.character_lucida_card,
        "vtnjwpo377h",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/tk8xtb/character_of_the_week_lucida_malephaise/"),
    ),
    Trias(
        R.string.character_trias,
        R.string.character_trias_full,
        R.string.character_trias_tagline,
        Complexity.Intermediate,
        Style.Tinkerer,

        R.drawable.character_trias_card,
        "iyn9znp8dn67",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/ssgwap/character_of_the_week_trias_blackwind/"),
        guides = arrayOf(
            Guide("Trias Blackwind Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/u50v99/trias_blackwind_fighter_guide/")),
        )
    ),
    Wardlaw(
        R.string.character_wardlaw,
        R.string.character_wardlaw_full,
        R.string.character_wardlaw_tagline,
        Complexity.Intermediate,
        Style.Slugger,

        R.drawable.character_wardlaw_card,
        "q5ux0xww1rem",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/smwg16/character_of_the_week_wardlaw_obrien/"),
        guides = arrayOf(
            Guide("Wardlaw O' Brien Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/u9j9gj/wardlaw_o_brien_fighter_guide/")),
        )
    ),

    // Wanderers
    Anya(
        R.string.character_anya,
        R.string.character_anya_full,
        R.string.character_anya_tagline,
        Complexity.Novice,
        Style.Disrupter,

        R.drawable.character_anya_card,
        "10mimmtc2q1n",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/mfvl22/character_of_the_week_anya_southwind/"),
    ),
    Cionaodh(
        R.string.character_cionaodh,
        R.string.character_cionaodh_full,
        R.string.character_cionaodh_tagline,
        Complexity.Advanced,
        Style.Tactician,

        R.drawable.character_cionaodh_card,
        "bsqc382we907",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/mm4ufi/character_of_the_week_cionaodh_ociaominhkenny/"),
        guides = arrayOf(
            Guide("Cionaodh O'Ciaominh (\"Kenny\") Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/ubq7ig/cionaodh_ociaominh_kenny_fighter_guide/")),
        )
    ),
    Dajiin(
        R.string.character_dajiin,
        R.string.character_dajiin_full,
        R.string.character_dajiin_tagline,
        Complexity.Intermediate,
        Style.Slugger,

        R.drawable.character_dajiin_card,
        "2ccbu1a04z1e",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/mpi5mp/character_of_the_week_dajiin_nidala/"),
        guides = arrayOf(
            Guide("Dajinn Nidala Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/mydyvh/dajinn_nidala_fighter_guide/")),
        )
    ),
    Feylana(
        R.string.character_feylana,
        R.string.character_feylana_full,
        R.string.character_feylana_tagline,
        Complexity.Intermediate,
        Style.Brawler,

        R.drawable.character_feylana_card,
        "w5y59b3rjalx",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/mu6gc3/character_of_the_week_feylana_chorgitz/"),
    ),
    John(
        R.string.character_john,
        R.string.character_john_full,
        R.string.character_john_tagline, // CHECK
        Complexity.Novice,
        Style.Slugger,

        R.drawable.character_john_card,
        "urxnb9hlfd78",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/mz54f9/character_of_the_week_john_strong_stevenson/"),
        guides = arrayOf(
            Guide("Fighter Guides: John Strong Stevenson", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/m9cxg6/fighter_guides_john_strong_stevenson/")),
        )
    ),
    Kai(
        R.string.character_kai,
        R.string.character_kai_full,
        R.string.character_kai_tagline,
        Complexity.Advanced,
        Style.Tinkerer,

        R.drawable.character_kai_card,
        "gg5kmjmuyls2",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/n49673/character_of_the_week_kai_omekai/"),
        guides = arrayOf(
            Guide("Kai Omekai Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/u2uc5x/kai_omekai_fighter_guide/")),
        )
    ),
    Kora(
        R.string.character_kora,
        R.string.character_kora_full,
        R.string.character_kora_tagline,
        Complexity.Novice,
        Style.Brawler,

        R.drawable.character_kora_card,
        "ewwiqrebgpca",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/n9d2zd/character_of_the_week_kora_sev_traxae/"),
        guides = arrayOf(
            Guide("Fighter guides: Kora Sev Traxae", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/mf44wt/fighter_guides_kora_sev_traxae/")),
        )
    ),
    Rin(
        R.string.character_rin,
        R.string.character_rin_full,
        R.string.character_rin_tagline,
        Complexity.Novice,
        Style.Mage,

        R.drawable.character_rin_card,
        "mmdhlsy31r18",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/neoo9y/character_of_the_week_rin_coldwater/"),
    ),
    Vekyl(
        R.string.character_vekyl,
        R.string.character_vekyl_full,
        R.string.character_vekyl_tagline,
        Complexity.Intermediate,
        Style.Tactician,

        R.drawable.character_vekyl_card,
        "akt3f5ci8440",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/nk7n2o/character_of_the_week_ser_vekyl_voroos/"),
    ),
    Wendy(
        R.string.character_wendy,
        R.string.character_wendy_full,
        R.string.character_wendy_tagline,
        Complexity.Intermediate,
        Style.Tactician,

        R.drawable.character_wendy_card,
        "p38w6s5z4149",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/npanv7/character_of_the_week_wendy_thrystle/"),
        guides = arrayOf(
            Guide("Wendy Thrystle Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/n3ab7d/wendy_thrystle_fighter_guide/")),
        )
    ),

    // Solo Fighters
    HimelNine(
        R.string.character_himelnine,
        R.string.character_himelnine_full,
        R.string.character_himelnine_tagline,
        Complexity.Novice,
        Style.Slugger,

        R.drawable.character_himelnine_card,
        null,
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/yx18eh/character_spotlight_himel_nine/"),
        guides = arrayOf(
            Guide("Himel Nine Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/mrjet3/himel_nine_fighter_guide/")),
        )
    ),
    Victor(
        R.string.character_victor,
        R.string.character_victor_full,
        R.string.character_victor_tagline,
        Complexity.Advanced,
        Style.Disrupter,

        R.drawable.character_victor_card,
        "ss9vk728s7g7",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/mav65n/character_of_the_week_victor/"),
        guides = arrayOf(
            Guide("Victor Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/uex3jo/victor_fighter_guide/")),
        )
    ),
    OriaxTwo(
        R.string.character_oriaxtwo,
        R.string.character_oriaxtwo_full,
        R.string.character_oriaxtwo_tagline,
        Complexity.Advanced,
        Style.Mage,

        R.drawable.character_oriaxtwo_card,
        "71t69jzgkyz2",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/lpq09x/character_of_the_week_oriax_two/"),
    ),
    Takeshi(
        R.string.character_takeshi,
        R.string.character_takeshi_full,
        R.string.character_takeshi_tagline,
        Complexity.Advanced,
        Style.Tinkerer,

        R.drawable.character_takeshi_card,
        "eymk7bcghc2p",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/m5qzfj/character_of_the_week_takeshi/"),
        guides = arrayOf(
            Guide("Takeshi Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/ufpqga/takeshi_fighter_guide/")),
        )
    ),
    Lucius(
        R.string.character_lucius,
        R.string.character_lucius_full,
        R.string.character_lucius_tagline,
        Complexity.Novice,
        Style.Brawler,

        R.drawable.character_lucius_card,
        "jlj7i9d2be38",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/lfiwpf/character_of_the_week_lucius/"),
    ),
    Andrus(
        R.string.character_andrus,
        R.string.character_andrus_full,
        R.string.character_andrus_tagline,
        Complexity.Novice,
        Style.Brawler,

        R.drawable.character_andrus_card,
        "xm88runqdr94",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/klzjar/character_of_the_week_andrus_dochartaigh/"),
        guides = arrayOf(
            Guide("Andrus Dochartaigh Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/mlg4tv/andrus_dochartaigh_fighter_guide/")),
        )
    ),
    Rheye(
        R.string.character_rheye,
        R.string.character_rheye_full,
        R.string.character_rheye_tagline,
        Complexity.Intermediate,
        Style.Tactician,

        R.drawable.character_rheye_card,
        "29bcz2jn9aqy",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/m0n3h7/character_of_the_week_rheye_cal/"),
    ),
    Eliza(
        R.string.character_eliza,
        R.string.character_eliza_full,
        R.string.character_eliza_tagline,
        Complexity.Advanced,
        Style.Mage,

        R.drawable.character_eliza_card,
        "rjqe8fmrwzab",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/kzzv9f/character_of_the_week_eliza/"),
        guides = arrayOf(
            Guide("Eliza Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/ug3cz3/eliza_fighter_guide/")),
        )
    ),
    EvilHikaru(
        R.string.character_evilhikaru,
        R.string.character_evilhikaru_full,
        R.string.character_evilhikaru_tagline,
        Complexity.Intermediate,
        Style.Disrupter,

        R.drawable.character_evilhikaru_card,
        "ll9dos65uz8f",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/l4sner/character_of_the_week_evil_hikaru/"),
    ),
    Anath(
        R.string.character_anath,
        R.string.character_anath_full,
        R.string.character_anath_tagline,
        Complexity.Intermediate,
        Style.Brawler,

        R.drawable.character_anath_card,
        "qk8vez32rikm",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/khnodu/character_of_the_week_anath_adrasteia/"),
    ),
    Claus(
        R.string.character_claus,
        R.string.character_claus_full,
        R.string.character_claus_tagline,
        Complexity.Intermediate,
        Style.Disrupter,

        R.drawable.character_claus_card,
        "44d92kr3blev",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/kqbskt/character_of_the_week_claus_wyndhal/"),
        guides = arrayOf(
            Guide("Claus & Wyndhal Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/uce5vb/claus_wyndhal_fighter_guide/")),
        )
    ),
    Dolores(
        R.string.character_dolores,
        R.string.character_dolores_full,
        R.string.character_dolores_tagline,
        Complexity.Intermediate,
        Style.Disrupter,

        R.drawable.character_dolores_card,
        "k8irdmgmrapz",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/kv81hk/character_of_the_week_dolores_malephaise/"),
        guides = arrayOf(
            Guide("Dolores Malephaise Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/u6m0jz/dolores_malephaise_fighter_guide/")),
        )
    ),
    Jin(
        R.string.character_jin,
        R.string.character_jin_full,
        R.string.character_jin_tagline,
        Complexity.Intermediate,
        Style.Tactician,

        R.drawable.character_jin_card,
        "9b6jiop6x2mn",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/la64es/character_of_the_week_jin/"),
        guides = arrayOf(
            Guide("Jin \"The Dark King\" Fighter Guide", "9spaceking", URL("https://www.reddit.com/r/Battlecon/comments/ur73lv/jin_the_dark_king_fighter_guide/")),
        )
    ),
    Merjoram(
        R.string.character_merjoram,
        R.string.character_merjoram_full,
        R.string.character_merjoram_tagline,
        Complexity.Novice,
        Style.Slugger,

        R.drawable.character_merjoram_card,
        "q33bjs19qgcu",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/lkke7f/character_of_the_week_merjoram_alexian/"),
    ),
    Raritti(
        R.string.character_raritti,
        R.string.character_raritti_full,
        R.string.character_raritti_tagline,
        Complexity.Advanced,
        Style.Mage,

        R.drawable.character_raritti_card,
        "7wfnwikb2g3z",
        cotw = URL("https://www.reddit.com/r/Battlecon/comments/lvha8t/character_of_the_week_raritti_sikhar/"),
    ),
//    Borneo(
//        R.string.character_borneo,
//        R.string.character_borneo_full,
//        R.string.character_borneo_tagline,
//        Complexity.,
//        Style.,
//
//        R.drawable.character_borneo_card,
//        "wtajnsgkmobw",
//    ),
//    Juto(
//        R.string.character_juto,
//        R.string.character_juto_full,
//        R.string.character_juto_tagline,
//        Complexity.,
//        Style.,
//
//        R.drawable.character_juto_card,
//        "36kwcnqx3fe",
//    ),

    ;
//    (
//        R.string.character_,
//        R.string.character__full,
//        R.string.character__tagline,
//        Complexity.,
//        Style.,
//
//        R.drawable.character__card,
//    ),

    companion object {
        private const val faqUrlBase = "https://docs.google.com/document/d/14-ayybFhvNdfo_IjsbNI0UDt8TXyQc9Qnxktti8Tswk/preview#heading=h."
    }

    val product
        get() = Product.lookup(this)

    val isPromo
        get() = product.isPromo

    val faqUrl: URL?
        get() = if (faqUrlBase == null) null else URL(faqUrlBase + faqHeading)
}