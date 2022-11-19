package com.softdream.exposicily

import android.app.Application
import android.content.Context

class ExpoSicilyApplication : Application() {
    init {
        app = this
    }

    companion object {
        private lateinit var app: ExpoSicilyApplication
        fun getAppContext(): Context = app.applicationContext
    }
}