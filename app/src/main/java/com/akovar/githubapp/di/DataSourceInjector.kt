package com.akovar.githubapp.di

import com.akovar.githubapp.data.source.repository.RepositoryDataSource
import com.akovar.githubapp.data.source.repository.RepositoryRepository
import com.akovar.githubapp.data.source.repository.local.LocalRepositoryDataSource
import com.akovar.githubapp.data.source.repository.remote.RemoteRepositoryDataSource
import com.akovar.githubapp.data.source.user.UserDataSource
import com.akovar.githubapp.data.source.user.UserRepository
import com.akovar.githubapp.data.source.user.local.LocalUserDataSource
import com.akovar.githubapp.data.source.user.remote.RemoteUserDataSource

/**
 * Created by akovar on 12/06/2020.
 */
class DataSourceInjector {

    companion object {

        private fun provideRepositoryLocalDataSource(): RepositoryDataSource {
            return LocalRepositoryDataSource()
        }

        private fun provideRepositoryRemoteDataSource(): RepositoryDataSource {
            return RemoteRepositoryDataSource(ApplicationInjector.provideRepositoryApi())
        }

        fun provideRepositoryDataSource(): RepositoryDataSource {
            return RepositoryRepository(
                provideRepositoryRemoteDataSource(),
                provideRepositoryLocalDataSource()
            )
        }

        private fun provideUserLocalDataSource(): UserDataSource {
            return LocalUserDataSource()
        }

        private fun provideUserRemoteDataSource(): UserDataSource {
            return RemoteUserDataSource(ApplicationInjector.provideUserApi())
        }

        fun provideUserDataSource(): UserDataSource {
            return UserRepository(
                provideUserRemoteDataSource(),
                provideUserLocalDataSource()
            )
        }
    }
}