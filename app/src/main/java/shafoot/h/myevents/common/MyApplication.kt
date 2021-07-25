/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.common

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import dagger.hilt.android.HiltAndroidApp
import shafoot.h.myevents.data.prefs.PreferencesConstants

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setAppTheme()
    }

    private fun setAppTheme() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val mode = prefs.getBoolean(PreferencesConstants.IS_NIGHT_MODE, false)
        if (mode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        }
    }


}