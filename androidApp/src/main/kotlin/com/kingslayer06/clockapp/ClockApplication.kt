package com.kingslayer06.clockapp

import android.app.Application
import com.kingslayer06.clockapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class ClockApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            config = { androidContext(this@ClockApplication) }
        )
    }
}