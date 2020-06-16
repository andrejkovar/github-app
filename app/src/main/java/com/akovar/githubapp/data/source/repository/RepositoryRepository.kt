package com.akovar.githubapp.data.source.repository

import com.akovar.githubapp.data.model.Repository
import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.base.BaseRepository

/**
 * Created by akovar on 10/06/2020.
 */
class RepositoryRepository(
    private val remoteDataSource: DataSource<Repository, RepositoryId>,
    private val localDataSource: DataSource<Repository, RepositoryId>
) : BaseRepository<Repository, RepositoryId>(),
    DataSource<Repository, RepositoryId> {

    override fun provideLocalDataSource(): DataSource<Repository, RepositoryId> = localDataSource

    override fun provideRemoteDataSource(): DataSource<Repository, RepositoryId> = remoteDataSource
}