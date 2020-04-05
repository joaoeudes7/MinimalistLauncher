package com.jedev.minimalistlauncher.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setupRecyclerView(context: Context, totalCache: Int = 20) {
    this.setHasFixedSize(true)
    this.layoutManager = LinearLayoutManager(context)
    this.setItemViewCacheSize(totalCache)
}