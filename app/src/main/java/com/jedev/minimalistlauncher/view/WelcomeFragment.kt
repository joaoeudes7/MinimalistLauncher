package com.jedev.minimalistlauncher.view

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jedev.minimalistlauncher.MainActivity
import com.jedev.minimalistlauncher.R

class WelcomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    private fun goToHome() {
        findNavController().navigate(R.id.action_welcomeFragment_to_homeFragment)
    }

    private fun requestDefaultLauncher() {
        val componentName = ComponentName(requireContext(), MainActivity::class.java)
        val selector = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME)

        requireContext().packageManager.apply {
            val enabled = PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            val roleNotKill =  PackageManager.DONT_KILL_APP

            setComponentEnabledSetting(componentName, enabled, roleNotKill)
        }

        startActivity(selector)



//        val startMain = Intent(Intent.ACTION_MAIN)
//        startMain.addCategory(Intent.CATEGORY_HOME)
//        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        startActivity(startMain)
    }
}