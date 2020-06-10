package com.ag04.githubapp.data.source.repository

import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.source.DataSource
import com.ag04.githubapp.data.source.DataSourceParam
import com.ag04.githubapp.data.source.Result
import com.ag04.githubapp.data.source.base.BaseRepository

/**
 * Created by akovar on 10/06/2020.
 */
class RepositoryRepository(
    private val remoteDataSource: DataSource<Long, Repository>,
    private val localDataSource: DataSource<Long, Repository>
) : BaseRepository<Long, Repository>() {

    override fun provideLocalDataSource(): DataSource<Long, Repository> = localDataSource

    override fun provideRemoteDataSource(): DataSource<Long, Repository> = remoteDataSource

    override suspend fun query(
        query: String,
        vararg params: DataSourceParam<String, String>
    ): Result<List<Long>> {
        TODO("Not yet implemented")
    }
}