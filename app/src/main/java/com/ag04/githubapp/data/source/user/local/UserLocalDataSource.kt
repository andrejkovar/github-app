package com.ag04.githubapp.data.source.user.local

import com.ag04.githubapp.data.model.User
import com.ag04.githubapp.data.source.DataSource
import com.ag04.githubapp.data.source.DataSourceParam
import com.ag04.githubapp.data.source.Result

/**
 * Created by akovar on 10/06/2020.
 */
class UserLocalDataSource : DataSource<Long, User> {

    override suspend fun getById(
        id: User,
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