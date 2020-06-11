package com.ag04.githubapp.data.source.repository

import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.source.base.AscSortOrder
import com.ag04.githubapp.data.source.base.DataSource
import com.ag04.githubapp.data.source.base.SortOrder

/**
 * Created by akovar on 11/06/2020.
 */
interface RepositoryDataSource :
    DataSource<Repository, Long> {

    suspend fun query(query: String, sort: RepositorySort?)
}

class RepositorySort(
    val stars: Boolean = false,
    val forks: Boolean = false,
    val lastUpdated: Boolean = false,
    sortOrder: SortOrder = AscSortOrder
)
