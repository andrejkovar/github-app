package com.akovar.githubapp.data.source.repository.local

import com.akovar.githubapp.data.model.Repository
import com.akovar.githubapp.data.source.Result
import com.akovar.githubapp.data.source.repository.RepositoryDataSource
import com.akovar.githubapp.data.source.repository.RepositorySort

/**
 * Created by akovar on 10/06/2020.
 */
class LocalRepositoryDataSource : RepositoryDataSource {

    override suspend fun query(
        query: String,
        sort: RepositorySort?
    ): Result<List<Repository>> {
        throw UnsupportedOperationException()
    }

    override suspend fun getUserRepository(
        userLogin: String,
        repoName: String
    ): Result<Repository> {
        throw UnsupportedOperationException()
    }

    override suspend fun getById(id: Long): Result<Repository> {
        throw UnsupportedOperationException()
    }

    override suspend fun getAll(): Result<List<Repository>> {
        throw UnsupportedOperationException()
    }

    override suspend fun save(item: Repository): Result<Repository> {
        throw UnsupportedOperationException()
    }

    override suspend fun saveAll(item: List<Repository>): Result<List<Repository>> {
        throw UnsupportedOperationException()
    }
}
