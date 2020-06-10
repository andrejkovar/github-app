package com.ag04.githubapp.data.source.repository.local

import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.source.DataSource
import com.ag04.githubapp.data.source.DataSourceParam
import com.ag04.githubapp.data.source.Result

/**
 * Created by akovar on 10/06/2020.
 */
class LocalRepositoryDataSource : DataSource<Long, Repository> {

    override suspend fun getById(
        id: Repository,
        vararg params: DataSourceParam<String, String>
    ): Result<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(vararg params: DataSourceParam<String, String>): Result<List<Long>> {
        TODO("Not yet implemented")
    }

    override suspend fun save(
        item: Long,
        vararg params: DataSourceParam<String, String>
    ): Result<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun saveAll(
        item: List<Long>,
        vararg params: DataSourceParam<String, String>
    ): Result<List<Long>> {
        TODO("Not yet implemented")
    }

    override suspend fun query(
        query: String,
        vararg params: DataSourceParam<String, String>
    ): Result<List<Long>> {
        TODO("Not yet implemented")
    }
}