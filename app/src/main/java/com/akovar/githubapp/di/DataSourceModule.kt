package com.akovar.githubapp.di

import com.akovar.githubapp.data.Constant
import com.akovar.githubapp.data.source.repository.RepositoryDataSource
import com.akovar.githubapp.data.source.repository.RepositoryRepository
import com.akovar.githubapp.data.source.repository.local.LocalRepositoryDataSource
import com.akovar.githubapp.data.source.repository.remote.RemoteRepositoryDataSource
import com.akovar.githubapp.data.source.repository.remote.RepositoryApi
import com.akovar.githubapp.data.source.user.UserDataSource
import com.akovar.githubapp.data.source.user.UserRepository
import com.akovar.githubapp.data.source.user.local.LocalUserDataSource
import com.akovar.githubapp.data.source.user.remote.RemoteUserDataSource
import com.akovar.githubapp.data.source.user.remote.UserApi
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by akovar on 14/06/2020.
 */
val dataSourceModule = module {

    factory<RepositoryDataSource>(named(Constant.DI.NAMED_REMOTE_REPOSITORY_DATA_SOURCE)) {
        RemoteRepositoryDataSource(get())
    }

    factory<RepositoryDataSource>(named(Constant.DI.NAMED_LOCAL_REPOSITORY_DATA_SOURCE)) {
        LocalRepositoryDataSource()
    }

    factory<RepositoryDataSource>(named(Constant.DI.NAMED_REPOSITORY_DATA_SOURCE)) {
        RepositoryRepository(
            get(qualifier(Constant.DI.NAMED_REMOTE_REPOSITORY_DATA_SOURCE)),
            get(qualifier(Constant.DI.NAMED_LOCAL_REPOSITORY_DATA_SOURCE))
        )
    }

    factory<UserDataSource>(named(Constant.DI.NAMED_REMOTE_USER_DATA_SOURCE)) {
        RemoteUserDataSource(get())
    }

    factory<UserDataSource>(named(Constant.DI.NAMED_LOCAL_USER_DATA_SOURCE)) {
        LocalUserDataSource()
    }

    factory<UserDataSource>(named(Constant.DI.NAMED_USER_DATA_SOURCE)) {
        UserRepository(
            get(qualifier(Constant.DI.NAMED_REMOTE_USER_DATA_SOURCE)),
            get(qualifier(Constant.DI.NAMED_LOCAL_USER_DATA_SOURCE))
        )
    }

    single<UserApi> {
        get<Retrofit>().create(UserApi::class.java)
    }

    single<RepositoryApi> {
        get<Retrofit>().create(RepositoryApi::class.java)
    }
}