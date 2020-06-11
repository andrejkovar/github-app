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
