package com.ag04.githubapp.data.source.base

/**
 * Created by akovar on 10/06/2020.
 */
abstract class BaseRepository<T, ID> :
    DataSource<T, ID> {

    abstract fun provideLocalDataSource(): DataSource<T, ID>

    abstract fun provideRemoteDataSource(): DataSource<T, ID>

    override suspend fun getById(id: ID): Result<T> {
        var result = provideLocalDataSource().getById(id)
        if (result is Success) {
            return result
        }

        result = provideRemoteDataSource().getById(id)
        if (result is Success) {
            provideLocalDataSource().save(result.item)
            return provideLocalDataSource().getById(id)
        }

        return result
    }

    override suspend fun getAll(): Result<List<T>> {
        var result = provideLocalDataSource().getAll()
        if (result is Success) {
            return result
        }

        result = provideRemoteDataSource().getAll()
        if (result is Success) {
            provideLocalDataSource().saveAll(result.item)
            return provideLocalDataSource().getAll()
        }

        return result
    }

    override suspend fun save(item: T): Result<T> {
        return provideLocalDataSource().save(item)
    }

    override suspend fun saveAll(item: List<T>): Result<List<T>> {
        return provideLocalDataSource().saveAll(item)
    }
}