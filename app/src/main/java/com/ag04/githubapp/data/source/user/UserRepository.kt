package com.ag04.githubapp.data.source.user

import com.ag04.githubapp.data.model.User
import com.ag04.githubapp.data.source.DataSource
import com.ag04.githubapp.data.source.base.BaseRepository

/**
 * Created by akovar on 10/06/2020.
 */
class UserRepository(
    private val remoteDataSource: DataSource<Long, User>,
    private val localDataSource: DataSource<Long, User>
) : BaseRepository<Long, User>() {

    override fun provideLocalDataSource(): DataSource<Long, User> = localDataSource

    override fun provideRemoteDataSource(): DataSource<Long, User> = remoteDataSource
}