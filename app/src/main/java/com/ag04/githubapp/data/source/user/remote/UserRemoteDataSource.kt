package com.ag04.githubapp.data.source.user.remote

import com.ag04.githubapp.data.model.User
import com.ag04.githubapp.data.source.DataSource
import com.ag04.githubapp.data.source.Result

/**
 * Created by akovar on 10/06/2020.
 */
class UserRemoteDataSource : DataSource<Long, User> {

    override suspend fun getById(id: User): Result<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): Result<List<Long>> {
        throw UnsupportedOperationException()
    }

    override suspend fun save(item: Long): Result<Long> {
        throw UnsupportedOperationException()
    }

    override suspend fun saveAll(item: List<Long>): Result<List<Long>> {
        throw UnsupportedOperationException()
    }
}