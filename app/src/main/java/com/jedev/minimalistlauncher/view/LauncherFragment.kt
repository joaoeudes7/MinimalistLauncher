package com.jedev.minimalistlauncher.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.jedev.minimalistlauncher.R
import com.jedev.minimalistlauncher.data.SharedViewModel
import com.jedev.minimalistlauncher.view.adapters.PkgAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_launcher.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LauncherFragment : Fragment() {
    private val vmShared by sharedViewModel<SharedViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_launcher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vmShared.getListApps(requireContext()).observe(viewLifecycleOwner, Observer {
            (list_apps_home.adapter as PkgAdapter).setItems(it.sortedBy { app -> app.name })
        })

        edt_search.requestFocus()
        edt_search.addTextChangedListener {
            it?.trim().let { charSequence ->
                if (charSequence.isNullOrEmpty()) {
                    onSearch(charSequence.toString())
                } else {
                    clearSearch()
                }
            }
        }

        openKeyboard()
    }

    private fun clearSearch() {
        TODO("Not yet implemented")
    }

    private fun onSearch(term: String) {
        TODO("Not yet implemented")
    }

    private fun openKeyboard() {
        (requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            showSoftInput(requireView(), InputMethodManager.SHOW_IMPLICIT)
        }
    }
}