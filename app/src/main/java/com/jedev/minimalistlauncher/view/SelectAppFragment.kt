package com.jedev.minimalistlauncher.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jedev.minimalistlauncher.R
import com.jedev.minimalistlauncher.data.SharedViewModel
import com.jedev.minimalistlauncher.data.model.AppEntity
import com.jedev.minimalistlauncher.utils.setupRecyclerView
import com.jedev.minimalistlauncher.view.adapters.PkgAdapter
import kotlinx.android.synthetic.main.activity_main.*

class SelectAppFragment : Fragment() {
    private lateinit var vmShared: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_select_app, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vmShared.getListApps(requireContext()).observe(viewLifecycleOwner, Observer {

        })
    }

    private fun openApp(appEntity: AppEntity) {

    }
}