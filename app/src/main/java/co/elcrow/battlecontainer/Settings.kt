package co.elcrow.battlecontainer

import android.content.Context
import androidx.annotation.StringRes
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

open class AppPreferences(private val preferences: Preferences?) {
    fun asMap(): Map<Preferences.Key<*>, Any> {
        return preferences?.asMap() ?: mapOf()
    }

    fun <T> contains(key: Preferences.Key<T>): Boolean {
        return preferences?.contains(key) ?: false
    }

    operator fun <T> get(key: Preferences.Key<T>): T? {
        return preferences?.get(key)
    }

    fun toMutablePreferences(): MutableAppPreferences {
        return MutableAppPreferences(preferences?.toMutablePreferences())
    }

    fun toPreferences(): AppPreferences {
        return AppPreferences(preferences?.toPreferences())
    }
}

class MutableAppPreferences(private val preferences: MutablePreferences?) : AppPreferences(preferences) {
    operator fun <T> set(key: Preferences.Key<T>, value: T) {
        preferences?.set(key, value)
    }

    operator fun plusAssign(prefs: Preferences) {
        preferences?.plusAssign(prefs)
    }

    operator fun plusAssign(pair: Preferences.Pair<*>) {
        preferences?.plusAssign(pair)
    }

    operator fun minusAssign(key: Preferences.Key<*>) {
        preferences?.minusAssign(key)
    }

    fun putAll(vararg pairs: Preferences.Pair<*>) {
        preferences?.putAll(*pairs)
    }

    fun <T> remove(key: Preferences.Key<T>): T? {
        // Not sure why this is not nullable in the original
        return preferences?.remove(key)
    }

    fun clear() {
        preferences?.clear()
    }

    override fun equals(other: Any?): Boolean =
        preferences?.equals(other) ?: (other == null)

    override fun hashCode(): Int =
        preferences?.hashCode() ?: 0

    override fun toString(): String =
        preferences?.toString() ?: "null"
}

fun Context.preferencesBlocking(): AppPreferences {
    return AppPreferences(runBlocking { dataStore.data.first() })
}

data class BooleanSetting internal constructor(val category: String, val name: String, @StringRes val descId: Int, val defaultValue: Boolean) {
    val key
        get() = booleanPreferencesKey("setting_${category}_$name")
}

object Settings {
    object Tracker {
        private const val category: String = "tracker"

        val randomActivePlayer = BooleanSetting(
            category,
            "randomactiveplayer",
            R.string.setting_tracker_randomactiveplayer,
            true
        )

        val confirmReset = BooleanSetting(
            category,
            "confirmreset",
            R.string.setting_tracker_confirmreset,
            true
        )
    }
}

fun AppPreferences.getSetting(setting: BooleanSetting): Boolean {
    return this[setting.key] ?: setting.defaultValue
}

fun MutableAppPreferences.setSetting(setting: BooleanSetting, value: Boolean) {
    this[setting.key] = value
}

fun MutableAppPreferences.toggleSetting(setting: BooleanSetting): Boolean {
    val value = !getSetting(setting)
    setSetting(setting, value)
    return value
}