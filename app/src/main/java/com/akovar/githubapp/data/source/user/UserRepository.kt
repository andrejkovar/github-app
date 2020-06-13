package com.akovar.githubapp.data.source.user

import com.akovar.githubapp.data.model.User
import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.base.BaseRepository

/**
 * Created by akovar on 10/06/2020.
 */
class UserRepository(
    private val remoteDataSource: DataSource<User, String>,
    private val localDataSource: DataSource<User, String>
) : BaseRepository<User, String>(),
    UserDataSource {

    override fun provideLocalDataSource(): DataSource<User, String> = localDataSource

    override fun provideRemoteDataSource(): DataSource<User, String> = remoteDataSource
}