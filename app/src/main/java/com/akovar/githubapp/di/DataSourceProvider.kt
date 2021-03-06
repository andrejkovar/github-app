package com.akovar.githubapp.di

import com.akovar.githubapp.data.model.Repository
import com.akovar.githubapp.data.model.User
import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.repository.RepositoryId
import com.akovar.githubapp.data.source.repository.RepositoryRepository
import com.akovar.githubapp.data.source.repository.local.LocalRepositoryDataSource
import com.akovar.githubapp.data.source.repository.remote.RemoteRepositoryDataSource
import com.akovar.githubapp.data.source.user.UserId
import com.akovar.githubapp.data.source.user.UserRepository
import com.akovar.githubapp.data.source.user.local.LocalUserDataSource
import com.akovar.githubapp.data.source.user.remote.RemoteUserDataSource

/**
 * Created by akovar on 12/06/2020.
 */
class DataSourceProvider {

    companion object {

        private fun provideRepositoryLocalDataSource(): DataSource<Repository, RepositoryId> {
            return LocalRepositoryDataSource()
        }

        private fun provideRepositoryRemoteDataSource(): DataSource<Repository, RepositoryId> {
            return RemoteRepositoryDataSource(ApplicationProvider.provideRepositoryApi())
        }

        fun provideRepositoryDataSource(): DataSource<Repository, RepositoryId> {
            return RepositoryRepository(
                provideRepositoryRemoteDataSource(),
                provideRepositoryLocalDataSource()
            )
        }

        private fun provideUserLocalDataSource(): DataSource<User, UserId> {
            return LocalUserDataSource()
        }

        private fun provideUserRemoteDataSource(): DataSource<User, UserId> {
            return RemoteUserDataSource(ApplicationProvider.provideUserApi())
        }

        fun provideUserDataSource(): DataSource<User, UserId> {
            return UserRepository(
                provideUserRemoteDataSource(),
                provideUserLocalDataSource()
            )
        }
    }
}