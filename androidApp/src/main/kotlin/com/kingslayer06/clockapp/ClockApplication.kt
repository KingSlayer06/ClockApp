package com.kingslayer06.clockapp

import android.app.Application
import com.kingslayer06.clockapp.di.initKoin

class ClockApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}