package com.akovar.githubapp.data.source.user.local

import com.akovar.githubapp.data.model.User
import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.Result
import com.akovar.githubapp.data.source.user.UserId

/**
 * Created by akovar on 10/06/2020.
 */
class LocalUserDataSource : DataSource<User, UserId> {

    override suspend fun getById(id: UserId): Result<User> {
        throw UnsupportedOperationException()
    }

    override suspend fun getAll(): Result<List<User>> {
        throw UnsupportedOperationException()
    }

    override suspend fun save(item: User): Result<User> {
        throw UnsupportedOperationException()
    }

    override suspend fun saveAll(items: List<User>): Result<List<User>> {
        throw UnsupportedOperationException()
    }
}