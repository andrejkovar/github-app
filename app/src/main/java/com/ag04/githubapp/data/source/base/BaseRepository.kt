package com.ag04.githubapp.data.source.base

import com.ag04.githubapp.data.source.DataSource
import com.ag04.githubapp.data.source.Result

/**
 * Created by akovar on 10/06/2020.
 */
abstract class BaseRepository<T, ID> : DataSource<T, ID> {

    abstract fun provideLocalDataSource(): DataSource<T, ID>

    abstract fun provideRemoteDataSource(): DataSource<T, ID>

    override suspend fun getById(id: ID): Result<T> {
        return provideRemoteDataSource().getById(id)
    }

    override suspend fun getAll(): Result<List<T>> {
        return provideRemoteDataSource().getAll()
    }

    override suspend fun save(item: T): Result<T> {
        throw UnsupportedOperationException()
    }

    override suspend fun saveAll(item: List<T>): Result<List<T>> {
        throw UnsupportedOperationException()
    }
}