package com.jedev.minimalistlauncher

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.jedev.minimalistlauncher.data.SharedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SharedViewModel() }
}

class MinimalistLauncher: Application(), ViewModelStoreOwner {
    private val appViewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MinimalistLauncher)
            modules(listOf(
                viewModelModule
            ))
        }
    }

    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }
}

