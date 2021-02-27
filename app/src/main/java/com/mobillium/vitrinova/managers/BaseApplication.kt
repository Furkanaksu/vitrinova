package com.mobillium.vitrinova.managers

import android.app.Application
import android.content.Context


 class BaseApplication : Application() {

    companion object {
        private var sApplication: Application? = null

        fun getApplication(): Application {
            return sApplication!!
        }

        fun getContext(): Context {
            return getApplication().applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        sApplication = this;

    }
}