package com.akovar.githubapp.data.source.repository.local

import com.akovar.githubapp.data.model.Repository
import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.Result
import com.akovar.githubapp.data.source.repository.RepositoryId

/**
 * Created by akovar on 10/06/2020.
 */
class LocalRepositoryDataSource : DataSource<Repository, RepositoryId> {

    override suspend fun getById(id: RepositoryId): Result<Repository> {
        throw UnsupportedOperationException()
    }

    override suspend fun getAll(): Result<List<Repository>> {
        throw UnsupportedOperationException()
    }

    override suspend fun getAll(id: RepositoryId): Result<List<Repository>> {
        throw UnsupportedOperationException()
    }

    override suspend fun save(item: Repository): Result<Repository> {
        throw UnsupportedOperationException()
    }

    override suspend fun saveAll(items: List<Repository>): Result<List<Repository>> {
        throw UnsupportedOperationException()
    }
}
