package com.jedev.minimalistlauncher

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    private val isFirstOpen = true
    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        if (isFirstOpen) {
            navController.navigate(R.id.welcomeFragment)
        }
    }

    override fun getTheme(): Resources.Theme? {
        val theme = super.getTheme()
        val useAlternativeTheme = true

        if (useAlternativeTheme) {
            theme.applyStyle(R.style.DEFAULT_MINIMALIST, true)
            // you could also use a switch if you have many themes that could apply
            return theme
        }

        return theme
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
