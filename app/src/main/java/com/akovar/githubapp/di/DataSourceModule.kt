package com.akovar.githubapp.di

import com.akovar.githubapp.data.source.repository.RepositoryDataSource
import com.akovar.githubapp.data.source.repository.RepositoryRepository
import com.akovar.githubapp.data.source.repository.local.LocalRepositoryDataSource
import com.akovar.githubapp.data.source.repository.remote.RemoteRepositoryDataSource
import com.akovar.githubapp.data.source.user.UserDataSource
import com.akovar.githubapp.data.source.user.UserRepository
import com.akovar.githubapp.data.source.user.local.LocalUserDataSource
import com.akovar.githubapp.data.source.user.remote.RemoteUserDataSource
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

/**
 * Created by akovar on 14/06/2020.
 */
val dataSourceModule = module {

    factory<RepositoryDataSource>(named("localRepositoryDataSource")) {
        LocalRepositoryDataSource()
    }

    factory<RepositoryDataSource>(named("remoteRepositoryDataSource")) {
        RemoteRepositoryDataSource(get())
    }

    factory<RepositoryDataSource>(named("repositoryDataSource")) {
        RepositoryRepository(
            get(qualifier("remoteRepositoryDataSource")),
            get(qualifier("localRepositoryDataSource"))
        )
    }

    factory<UserDataSource>(named("localUserDataSource")) {
        LocalUserDataSource()
    }

    factory<UserDataSource>(named("remoteUserDataSource")) {
        RemoteUserDataSource(get())
    }

    factory<UserDataSource>(named("userDataSource")) {
        UserRepository(
            get(qualifier("remoteUserDataSource")),
            get(qualifier("localUserDataSource"))
        )
    }
}