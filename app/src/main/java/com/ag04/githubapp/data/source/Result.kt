package com.ag04.githubapp.data.source

/**
 * Created by akovar on 10/06/2020.
 */
open class Result<T>(open val item: T?, open val error: Throwable?) {

    class Success<T>(override val item: T) : Result<T>(item, null)

    class Error<T>(override val error: Throwable) : Result<T>(null, error)
}
