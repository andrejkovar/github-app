package com.ag04.githubapp.data.source

/**
 * Created by akovar on 10/06/2020.
 */
interface DataSource<T, ID> {

    suspend fun getById(id: ID): Result<T>

    suspend fun getAll(): Result<List<T>>

    suspend fun save(item: T): Result<T>

    suspend fun saveAll(item: List<T>): Result<List<T>>
}

class DataSourceError {

    companion object {

        const val CODE_IO_ERROR = 0
        const val CODE_UNSUCCESSFUL_ERROR = 1
        const val C0DE_UNKNOWN_ERR0R = Int.MAX_VALUE
    }
}
