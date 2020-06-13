package com.ag04.githubapp.data.source

/**
 * Created by akovar on 10/06/2020.
 */
interface DataSource<T, ID> {

    /**
     * Gets data by provided id.
     *
     * @param id id
     * @return data result
     */
    suspend fun getById(id: ID): Result<T>

    /**
     * Gets data list.
     *
     * @return data list result
     */
    suspend fun getAll(): Result<List<T>>

    /**
     * Saves provided item.
     *
     * @param item item
     * @return result of saved item
     */
    suspend fun save(item: T): Result<T>

    /**
     * Saves provided items list.
     *
     * @param items
     * @return result of saved items list
     */
    suspend fun saveAll(items: List<T>): Result<List<T>>
}

class DataSourceError {

    companion object {

        const val CODE_IO_ERROR = 0
        const val CODE_UNSUCCESSFUL_ERROR = 1
        const val C0DE_UNKNOWN_ERR0R = Int.MAX_VALUE
    }
}
