package com.jedev.minimalistlauncher.data

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jedev.minimalistlauncher.data.model.AppEntity
import java.util.*

class SharedViewModel : ViewModel() {
    fun getCurrentHours() = liveData {
        val cal = Calendar.getInstance()
        val hours = cal.get(Calendar.HOUR)
        val minutes = cal.get(Calendar.MINUTE)

        emit("${hours}:${minutes}")
    }

    fun getAppsHome() = liveData {
        val apps = listOf<AppEntity>()

        emit(apps)
    }

    fun getListApps(context: Context) = liveData {
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)

        val infoPkgs = context.packageManager.queryIntentActivities(mainIntent, 0)
        val apps = infoPkgs.map { pkg -> AppEntity.fromResolveInfo(context, pkg) }

        emit(apps)
    }

    fun lastTimeOpened() {

    }
}
