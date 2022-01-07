package com.testexample.pokeapi.ui.activities.main

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.testexample.pokeapi.R
import com.testexample.pokeapi.databinding.ActivityMainBinding
import com.testexample.utils.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController

    override fun getLayout() = R.layout.activity_main

    override fun initView() {
        initElements()
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.mainContainerFragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun initObservers() {

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navController.navigateUp() || super.onSupportNavigateUp()
    }

}