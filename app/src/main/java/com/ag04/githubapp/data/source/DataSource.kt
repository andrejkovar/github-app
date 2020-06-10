package com.ag04.githubapp.data.source

/**
 * Created by akovar on 10/06/2020.
 */
interface DataSource<T, ID> {

    suspend fun getById(
        id: ID,
        vararg params: DataSourceParam<String, String>
    ): Result<T>

    suspend fun getAll(
        vararg params: DataSourceParam<String, String>
    ): Result<List<T>>

    suspend fun save(
        item: T,
        vararg params: DataSourceParam<String, String>
    ): Result<T>

    suspend fun saveAll(
        item: List<T>,
        vararg params: DataSourceParam<String, String>
    ): Result<List<T>>

    suspend fun query(
        query: String,
        vararg params: DataSourceParam<String, String>
    ): Result<List<T>>
}

interface DataSourceParam<K, V> {

    fun getKey(): K

    fun getValue(): V
}

