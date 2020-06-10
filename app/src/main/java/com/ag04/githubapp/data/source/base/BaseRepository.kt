package com.ag04.githubapp.data.source.base

import com.ag04.githubapp.data.source.DataSource
import com.ag04.githubapp.data.source.DataSourceParam
import com.ag04.githubapp.data.source.Result
import com.ag04.githubapp.data.source.Success

/**
 * Created by akovar on 10/06/2020.
 */
abstract class BaseRepository<T, ID>() :
    DataSource<T, ID> {

    abstract fun provideLocalDataSource(): DataSource<T, ID>

    abstract fun provideRemoteDataSource(): DataSource<T, ID>

    override suspend fun getById(
        id: ID,
        vararg params: DataSourceParam<String, String>
    ): Result<T> {

        var result = provideLocalDataSource().getById(id, *params)
        if (result is Success) {
            return result
        }

        result = provideRemoteDataSource().getById(id, *params)
        if (result is Success) {
            provideLocalDataSource().save(result.item)
            return provideLocalDataSource().getById(id, *params)
        }

        return result
    }

    override suspend fun getAll(
        vararg params: DataSourceParam<String, String>
    ): Result<List<T>> {

        var result = provideLocalDataSource().getAll(*params)
        if (result is Success) {
            return result
        }

        result = provideRemoteDataSource().getAll(*params)
        if (result is Success) {
            provideLocalDataSource().saveAll(result.item)
            return provideLocalDataSource().getAll(*params)
        }

        return result
    }

    override suspend fun save(item: T, vararg params: DataSourceParam<String, String>): Result<T> {
        return provideLocalDataSource().save(item, *params)
    }

    override suspend fun saveAll(
        item: List<T>,
        vararg params: DataSourceParam<String, String>
    ): Result<List<T>> {
        return provideLocalDataSource().saveAll(item, *params)
    }
}