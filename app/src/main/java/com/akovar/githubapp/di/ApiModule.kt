package com.akovar.githubapp.di

import com.akovar.githubapp.data.source.repository.remote.RepositoryApi
import com.akovar.githubapp.data.source.user.remote.UserApi
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by akovar on 14/06/2020.
 */
val apiModule = module {

    single<UserApi> {
        get<Retrofit>().create(UserApi::class.java)
    }

    single<RepositoryApi> {
        get<Retrofit>().create(RepositoryApi::class.java)
    }
}