package com.akovar.githubapp.data.source.repository

import com.akovar.githubapp.data.model.Repository
import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.Result

/**
 * Created by akovar on 11/06/2020.
 */
interface RepositoryDataSource : DataSource<Repository, Long> {

    suspend fun query(
        query: String,
        sort: RepositorySort?
    ): Result<List<Repository>>

    suspend fun getUserRepository(
        userLogin: String,
        repoName: String
    ): Result<Repository>
}

class RepositorySort(
    val stars: Boolean,
    val forks: Boolean,
    val lastUpdated: Boolean
)
