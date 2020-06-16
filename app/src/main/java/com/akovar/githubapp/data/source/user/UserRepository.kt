package com.akovar.githubapp.data.source.user

import com.akovar.githubapp.data.model.User
import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.base.BaseRepository

/**
 * Created by akovar on 10/06/2020.
 */
class UserRepository(
    private val remoteDataSource: DataSource<User, UserId>,
    private val localDataSource: DataSource<User, UserId>
) : BaseRepository<User, UserId>(),
    DataSource<User, UserId> {

    override fun provideLocalDataSource(): DataSource<User, UserId> = localDataSource

    override fun provideRemoteDataSource(): DataSource<User, UserId> = remoteDataSource
}