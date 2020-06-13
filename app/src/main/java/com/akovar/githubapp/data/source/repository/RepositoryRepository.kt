package com.akovar.githubapp.data.source.repository

import com.akovar.githubapp.data.model.Repository
import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.Result
import com.akovar.githubapp.data.source.base.BaseRepository

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

    override suspend fun getUserRepository(
        userLogin: String,
        repoName: String
    ): Result<Repository> {
        return remoteDataSource.getUserRepository(userLogin, repoName)
    }
}