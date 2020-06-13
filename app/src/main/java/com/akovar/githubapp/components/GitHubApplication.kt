package com.akovar.githubapp.components

import android.app.Application
import com.akovar.githubapp.BuildConfig
import com.akovar.githubapp.di.apiModule
import com.akovar.githubapp.di.applicationModule
import com.akovar.githubapp.di.fragmentModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created by akovar on 09/06/2020.
 */
class GitHubApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GitHubApplication)
            fragmentFactory()

            modules(
                applicationModule,
                apiModule,
                fragmentModule
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}