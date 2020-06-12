package com.ag04.githubapp.components.injector

import com.ag04.githubapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by akovar on 12/06/2020.
 */
class ApplicationInjector {

    companion object {

        private val retrofitInstance: Retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        fun provideRetrofit(): Retrofit {
            return retrofitInstance
        }
    }
}