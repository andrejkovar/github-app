package com.akovar.githubapp.data.source.repository

/**
 * Created by akovar on 16/06/2020.
 */
sealed class RepositoryId

class Query(
    val query: String,
    val sort: RepositorySort?
) : RepositoryId()

class UserRepository(
    val userLogin: String,
    val repoName: String
) : RepositoryId()

data class RepositorySort(
    val stars: Boolean,
    val forks: Boolean,
    val lastUpdated: Boolean
)