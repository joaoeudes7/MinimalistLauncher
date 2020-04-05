package com.jedev.minimalistlauncher.view.adapters

import com.jedev.minimalistlauncher.R
import com.jedev.minimalistlauncher.data.model.AppEntity
import kotlinx.android.synthetic.main.item_app.view.*

class PkgAdapter(
    onClickListener: (appEntity: AppEntity) -> Unit = {},
    onLongClickListener: (appEntity: AppEntity) -> Unit = {}
) : AdapterGeneric<AppEntity>(
    resourceLayout = R.layout.item_app,
    onBindView = { view, item ->
        view.apply {
            txt_app_name.text = item.name

            setOnClickListener {
                onClickListener.invoke(item)
            }

            setOnLongClickListener {
                onLongClickListener.invoke(item)
                return@setOnLongClickListener true
            }
        }
    }
)