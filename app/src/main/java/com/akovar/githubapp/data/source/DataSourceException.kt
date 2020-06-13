package com.akovar.githubapp.data.source

/**
 * Created by akovar on 12/06/2020.
 */
data class DataSourceException(
    val code: Int,
    override val message: String?,
    override val cause: Throwable?
) : Throwable(message, cause)
