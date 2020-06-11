package com.ag04.githubapp.data.source.repository.local

import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.source.base.Result
import com.ag04.githubapp.data.source.base.Success
import com.ag04.githubapp.data.source.repository.RepositoryDataSource
import com.ag04.githubapp.data.source.repository.RepositorySort

/**
 * Created by akovar on 10/06/2020.
 */
class LocalRepositoryDataSource : RepositoryDataSource {

    override suspend fun query(query: String, sort: RepositorySort?) {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Long): Result<Repository> {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): Result<List<Repository>> {
        return Success(listOf(Repository(), Repository(), Repository(), Repository(), Repository()))
    }

    override suspend fun save(item: Repository): Result<Repository> {
        TODO("Not yet implemented")
    }

    override suspend fun saveAll(item: List<Repository>): Result<List<Repository>> {
        TODO("Not yet implemented")
    }
}
