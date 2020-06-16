package com.akovar.githubapp.data.source.user

import com.akovar.githubapp.data.model.User
import com.akovar.githubapp.data.source.Result
import com.akovar.githubapp.data.source.base.BaseRepository

/**
 * Created by akovar on 10/06/2020.
 */
class UserRepository(
    private val remoteDataSource: UserDataSource,
    private val localDataSource: UserDataSource
) : BaseRepository<User, String>(),
    UserDataSource {

    override fun provideLocalDataSource(): UserDataSource = localDataSource

    override fun provideRemoteDataSource(): UserDataSource = remoteDataSource

    override suspend fun getMe(): Result<User> {
        return remoteDataSource.getMe()
    }
}