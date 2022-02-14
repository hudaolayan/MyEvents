/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import shafoot.h.myevents.R
import shafoot.h.myevents.databinding.ActivityMainBinding
import shafoot.h.myevents.ui.base.BaseActivity
import shafoot.h.myevents.ui.base.BaseBindingActivity

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initNavigation()
    }

    private fun initNavigation() {
        navController = findNavController(R.id.mainNavHostFragment)
             binding.mainBottomNav.setupWithNavController(navController)

    }

    companion object {

        fun startWithClearStack(context: Context?) {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(intent)
        }
    }
}