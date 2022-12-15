package com.softdream.exposicily

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ExpoSicilyApplication : Application() {
    init {
        app = this
    }

    companion object {
        private lateinit var app: ExpoSicilyApplication
        fun getAppContext(): Context = app.applicationContext
    }
}