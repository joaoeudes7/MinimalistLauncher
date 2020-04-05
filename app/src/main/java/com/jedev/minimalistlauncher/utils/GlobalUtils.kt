package com.jedev.minimalistlauncher.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import android.os.VibrationEffect
import splitties.systemservices.vibrator

fun onVibrate() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        vibrator.vibrate(VibrationEffect.createOneShot(100, 150))
    } else {
        vibrator.vibrate(150)
    }
}

fun openApp(context: Context, pkg: String) {
    onVibrate()

    val intent = context.packageManager.getLaunchIntentForPackage(pkg)
    context.startActivity(intent)
}

fun getDefaultApp(context: Context, category: String): ResolveInfo {
    val localPackageManager = context.packageManager
    val intent = Intent(Intent.ACTION_MAIN).addCategory(category)

    return localPackageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)!!
}