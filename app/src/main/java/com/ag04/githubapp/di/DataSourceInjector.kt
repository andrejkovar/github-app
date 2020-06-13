package com.ag04.githubapp.di

import com.ag04.githubapp.data.source.repository.RepositoryDataSource
import com.ag04.githubapp.data.source.repository.RepositoryRepository
import com.ag04.githubapp.data.source.repository.local.LocalRepositoryDataSource
import com.ag04.githubapp.data.source.repository.remote.RemoteRepositoryDataSource
import com.ag04.githubapp.data.source.repository.remote.RepositoryApi
import com.ag04.githubapp.data.source.user.UserDataSource
import com.ag04.githubapp.data.source.user.UserRepository
import com.ag04.githubapp.data.source.user.local.LocalUserDataSource
import com.ag04.githubapp.data.source.user.remote.RemoteUserDataSource
import com.ag04.githubapp.data.source.user.remote.UserApi

/**
 * Created by akovar on 12/06/2020.
 */
class DataSourceInjector {

    companion object {

        private fun provideRepositoryLocalDataSource(): RepositoryDataSource {
            return LocalRepositoryDataSource()
        }

        private fun provideRepositoryRemoteDataSource(): RepositoryDataSource {
            return RemoteRepositoryDataSource(provideRepositoryApi())
        }

        private fun provideRepositoryApi(): RepositoryApi {
            return ApplicationInjector.provideRetrofit().create(RepositoryApi::class.java)
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
            return RemoteUserDataSource(provideUserApi())
        }

        private fun provideUserApi(): UserApi {
            return ApplicationInjector.provideRetrofit().create(UserApi::class.java)
        }

        fun provideUserDataSource(): UserDataSource {
            return UserRepository(
                provideUserRemoteDataSource(),
                provideUserLocalDataSource()
            )
        }
    }
}