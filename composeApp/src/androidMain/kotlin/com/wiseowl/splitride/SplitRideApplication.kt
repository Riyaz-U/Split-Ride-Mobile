package com.wiseowl.splitride

import android.app.Application
import com.wiseowl.splitride.core.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class SplitRideApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@SplitRideApplication)
            androidLogger()
        }
    }
}