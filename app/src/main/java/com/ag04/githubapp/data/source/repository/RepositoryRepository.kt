package com.ag04.githubapp.data.source.repository

import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.source.base.BaseRepository
import com.ag04.githubapp.data.source.base.DataSource

/**
 * Created by akovar on 10/06/2020.
 */
class RepositoryRepository(
    private val remoteDataSource: DataSource<Repository, Long>,
    private val localDataSource: DataSource<Repository, Long>
) : BaseRepository<Repository, Long>(),
    RepositoryDataSource {

    override fun provideLocalDataSource(): DataSource<Repository, Long> = localDataSource

    override fun provideRemoteDataSource(): DataSource<Repository, Long> = remoteDataSource

    override suspend fun query(query: String, sort: RepositorySort?) {
        TODO("Not yet implemented")
    }
}