/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.data.prefs

import android.content.SharedPreferences
import shafoot.h.myevents.common.enums.Language

class PrefsImpl(private val prefs: SharedPreferences) : Prefs {

    override var language: String
        get() = prefs.getString(PreferencesConstants.LANGUAGE, Language.English.value)
            ?: Language.English.value
        set(value) = prefs.edit().putString(PreferencesConstants.LANGUAGE, value).apply()

    override var isNightMode: Boolean
        get() = prefs.getBoolean(PreferencesConstants.IS_NIGHT_MODE, false)
        set(value) = prefs.edit().putBoolean(PreferencesConstants.IS_NIGHT_MODE, value).apply()
}