package com.ag04.githubapp.di

import com.ag04.githubapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by akovar on 12/06/2020.
 */
class ApplicationInjector {

    companion object {

        private val client: OkHttpClient = OkHttpClient.Builder()
            .build()

        private val retrofitInstance: Retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        fun provideRetrofit(): Retrofit {
            return retrofitInstance
        }
    }
}