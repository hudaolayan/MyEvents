/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.more

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.AndroidEntryPoint
import shafoot.h.myevents.R
import shafoot.h.myevents.common.enums.Language
import shafoot.h.myevents.data.prefs.Prefs
import shafoot.h.myevents.databinding.FragmentMoreBinding
import shafoot.h.myevents.ui.base.BaseBindingFragment
import shafoot.h.myevents.ui.main.MainActivity
import javax.inject.Inject

@AndroidEntryPoint
class MoreFragment : BaseBindingFragment<FragmentMoreBinding>() {

    override val layoutId: Int = R.layout.fragment_more

    @Inject
    lateinit var preferences: Prefs

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        val language = if (preferences.language == Language.English.value) {
            context?.getString(R.string.arabic)
        } else {
            context?.getString(R.string.english)
        }

        binding?.otherLanguageTxt?.text = language

        val isNightMode = preferences.isNightMode
        binding?.nightModeSwitch?.isChecked = isNightMode

        binding?.nightModeSwitch?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                preferences.isNightMode=true
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                preferences.isNightMode=false
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }

        binding?.otherLanguageTxt?.setOnClickListener {
            if (preferences.language == Language.English.value) {
                preferences.language = Language.Arabic.value

            } else {
                preferences.language = Language.English.value
            }
            MainActivity.startWithClearStack(requireContext())
        }
    }


}
