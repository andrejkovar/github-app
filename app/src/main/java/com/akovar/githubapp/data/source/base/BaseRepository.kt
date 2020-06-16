package com.akovar.githubapp.data.source.base

import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.Result

/**
 * Created by akovar on 10/06/2020.
 */
abstract class BaseRepository<T, ID> : DataSource<T, ID> {

    /**
     * Provides local data source.
     *
     * @return local data source
     */
    abstract fun provideLocalDataSource(): DataSource<T, ID>

    /**
     * Provides remote data source.
     *
     * @return remote data source
     */
    abstract fun provideRemoteDataSource(): DataSource<T, ID>

    override suspend fun getById(id: ID): Result<T> {
        return provideRemoteDataSource().getById(id)
    }

    override suspend fun getAll(): Result<List<T>> {
        return provideRemoteDataSource().getAll()
    }

    override suspend fun getAll(id: ID): Result<List<T>> {
        return provideRemoteDataSource().getAll(id)
    }

    override suspend fun save(item: T): Result<T> {
        throw UnsupportedOperationException()
    }

    override suspend fun saveAll(items: List<T>): Result<List<T>> {
        throw UnsupportedOperationException()
    }
}