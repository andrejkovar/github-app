package com.akovar.githubapp.data.source.base

import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.Result

/**
 * Created by akovar on 13/06/2020.
 */
abstract class BaseRemoteDataSource<T, ID> : DataSource<T, ID> {

    override suspend fun getById(id: ID): Result<T> {
        throw UnsupportedOperationException()
    }

    override suspend fun getAll(): Result<List<T>> {
        throw UnsupportedOperationException()
    }

    override suspend fun save(item: T): Result<T> {
        throw UnsupportedOperationException()
    }

    override suspend fun saveAll(items: List<T>): Result<List<T>> {
        throw UnsupportedOperationException()
    }
}