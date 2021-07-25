/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import shafoot.h.myevents.common.MyContextWrapper
import shafoot.h.myevents.common.enums.Language
import shafoot.h.myevents.data.prefs.PreferencesConstants


abstract class BaseActivity() : AppCompatActivity() {


    override fun attachBaseContext(newBase: Context) {
        val language = PreferenceManager.getDefaultSharedPreferences(newBase)
            .getString(PreferencesConstants.LANGUAGE, Language.English.value)
        super.attachBaseContext(MyContextWrapper.wrap(newBase, language ?: Language.English.value))
    }
}