package com.akovar.githubapp.components

import android.app.Application
import com.akovar.githubapp.BuildConfig
import timber.log.Timber

/**
 * Created by akovar on 09/06/2020.
 */
class GitHubApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}