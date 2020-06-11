package com.ag04.githubapp.data.source.user.local

import com.ag04.githubapp.data.model.User
import com.ag04.githubapp.data.source.DataSource
import com.ag04.githubapp.data.source.Result

/**
 * Created by akovar on 10/06/2020.
 */
class UserLocalDataSource : DataSource<Long, User> {

    override suspend fun getById(id: User): Result<Long> {
        throw UnsupportedOperationException()
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