/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.common

import android.annotation.TargetApi
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import java.util.*

class MyContextWrapper(base: Context) : ContextWrapper(base) {
    companion object {

        fun wrap(mContext: Context, language: String): ContextWrapper {
            var context = mContext
            val config = context.resources.configuration
            val locale = Locale(language)
            Locale.setDefault(locale)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setSystemLocale(config, locale)
            } else {
                setSystemLocaleLegacy(config, locale)
            }
            context = context.createConfigurationContext(config)
            return MyContextWrapper(context)
        }

        private fun setSystemLocaleLegacy(config: Configuration, locale: Locale) {
            config.setLayoutDirection(locale)
            config.setLocale(locale)
        }

        @TargetApi(Build.VERSION_CODES.N)
        fun setSystemLocale(config: Configuration, locale: Locale) {
            config.setLayoutDirection(locale)
            config.setLocale(locale)
        }
    }
}