package com.ag04.githubapp.data.source.repository

import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.source.DataSource
import com.ag04.githubapp.data.source.Result

/**
 * Created by akovar on 11/06/2020.
 */
interface RepositoryDataSource : DataSource<Repository, Long> {

    suspend fun query(
        query: String,
        sort: RepositorySort?
    ): Result<List<Repository>>
}

class RepositorySort(
    val stars: Boolean,
    val forks: Boolean,
    val lastUpdated: Boolean
)
