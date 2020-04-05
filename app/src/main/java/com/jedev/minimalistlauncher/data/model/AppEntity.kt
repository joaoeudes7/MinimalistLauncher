package com.jedev.minimalistlauncher.data.model

import android.content.Context
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import com.jedev.minimalistlauncher.utils.onVibrate
import com.jedev.minimalistlauncher.utils.openApp

class AppEntity (
    val name: String,
    val pkg: String,
    val icon: Drawable
) {
    companion object {
        fun fromResolveInfo(context: Context, resolveInfo: ResolveInfo): AppEntity {
            val name = resolveInfo.activityInfo.loadLabel(context.packageManager).toString()
            val pkg = resolveInfo.activityInfo.packageName
            val icon = resolveInfo.loadIcon(context.packageManager)

            return AppEntity(name, pkg, icon)
        }
    }

    fun open(context: Context) {
        onVibrate()
        openApp(context, pkg)
    }
}