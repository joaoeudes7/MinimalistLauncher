package com.jedev.minimalistlauncher.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jedev.minimalistlauncher.R
import com.jedev.minimalistlauncher.data.SharedViewModel
import com.jedev.minimalistlauncher.data.model.AppEntity
import com.jedev.minimalistlauncher.utils.OnSwipeTouchListener
import com.jedev.minimalistlauncher.utils.getDefaultApp
import com.jedev.minimalistlauncher.utils.setupRecyclerView
import com.jedev.minimalistlauncher.view.adapters.PkgAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private val vmShared by sharedViewModel<SharedViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            vmShared.getAppsHome().observe(viewLifecycleOwner, Observer {
                (list_apps_home.adapter as PkgAdapter).setItems(it)
            })

            vmShared.getCurrentHours().observe(viewLifecycleOwner, Observer {
                txt_clock.text = it
            })
        }

        rootView.setOnTouchListener(object : OnSwipeTouchListener(requireContext()) {
            override fun onSwipeBottom() {
                onOpenStatusBar()
            }

            override fun onSwipeTop() {
                onOpenLauncher()
            }

            override fun onSwipeLeft() {
                onOpenLeftApp()
            }

            override fun onSwipeRight() {
                onOpenRightApp()
            }
        })

        list_apps_home.apply {
            setupRecyclerView(requireContext())
            adapter = PkgAdapter(::openApp, ::onLongClick)
        }

        txt_clock.setOnClickListener {
            openClock()
        }

        txt_calendar.setOnClickListener {
            openCalendar()
        }
    }

    private fun onLongClick(appEntity: AppEntity) {
        findNavController().navigate(R.id.action_homeFragment_to_launcherFragment)
    }

    private fun onOpenLauncher() {
        findNavController().navigate(R.id.action_homeFragment_to_launcherFragment)
    }

    private fun openSettings() {
        findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
    }

    private fun onOpenStatusBar() {
        openCalendar()
    }

    private fun onOpenLeftApp() {
        openCamera()
    }

    private fun onOpenRightApp() {
        openCalendar()
    }

    private fun openCamera() {
        openDefaultAppOfCategory(Intent.ACTION_CAMERA_BUTTON)
    }

    private fun openClock() {
        openDefaultAppOfCategory(Intent.ACTION_QUICK_CLOCK)
    }

    private fun openCalendar() {
        openDefaultAppOfCategory(Intent.CATEGORY_APP_CALENDAR)
    }

    private fun openApp(appEntity: AppEntity) {
        appEntity.open(requireContext())
    }

    private fun openDefaultAppOfCategory(category: String) {
        val appDefault = getDefaultApp(requireContext(), category)
        val appEntity = AppEntity.fromResolveInfo(requireContext(), appDefault)

        openApp(appEntity)
    }

}