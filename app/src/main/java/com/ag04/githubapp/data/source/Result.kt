package com.ag04.githubapp.data.source

/**
 * Created by akovar on 10/06/2020.
 */
sealed class Result<T>(open val item: T?, open val error: Throwable?) {

    data class Success<T>(override val item: T) : Result<T>(item, null)

    data class Error<T>(override val error: DataSourceException) : Result<T>(null, error)
}
