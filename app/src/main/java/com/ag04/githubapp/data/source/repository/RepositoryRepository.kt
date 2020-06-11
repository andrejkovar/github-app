package com.ag04.githubapp.data.source.repository

import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.source.DataSource
import com.ag04.githubapp.data.source.Result
import com.ag04.githubapp.data.source.base.BaseRepository

/**
 * Created by akovar on 10/06/2020.
 */
class RepositoryRepository(
    private val remoteDataSource: RepositoryDataSource,
    private val localDataSource: RepositoryDataSource
) : BaseRepository<Repository, Long>(),
    RepositoryDataSource {

    override fun provideLocalDataSource(): DataSource<Repository, Long> = localDataSource

    override fun provideRemoteDataSource(): DataSource<Repository, Long> = remoteDataSource

    override suspend fun query(query: String, sort: RepositorySort?): Result<List<Repository>> {
        return remoteDataSource.query(query, sort)
    }
}