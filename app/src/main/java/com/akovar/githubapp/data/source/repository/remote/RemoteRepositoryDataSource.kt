package com.akovar.githubapp.data.source.repository.remote

import com.akovar.githubapp.data.Constant
import com.akovar.githubapp.data.model.Repository
import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.DataSourceException
import com.akovar.githubapp.data.source.RemoteDataSourceHelper
import com.akovar.githubapp.data.source.Result
import com.akovar.githubapp.data.source.repository.Query
import com.akovar.githubapp.data.source.repository.RepositoryId
import com.akovar.githubapp.data.source.repository.RepositorySort
import com.akovar.githubapp.data.source.repository.UserRepository
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by akovar on 10/06/2020.
 */
class RemoteRepositoryDataSource(
    private val repositoryApi: RepositoryApi
) : DataSource<Repository, RepositoryId> {

    override suspend fun getById(id: RepositoryId): Result<Repository> {
        return RemoteDataSourceHelper.processGet {
            when (id) {
                is UserRepository -> repositoryApi.getOwnerRepo(id.userLogin, id.repoName)
                else -> throw UnsupportedOperationException()
            }
        }
    }

    override suspend fun getAll(): Result<List<Repository>> {
        throw UnsupportedOperationException()
    }

    override suspend fun getAll(id: RepositoryId): Result<List<Repository>> {
        return when (id) {
            is Query -> query(id.query, id.sort)
            else -> throw UnsupportedOperationException()
        }
    }

    override suspend fun save(item: Repository): Result<Repository> {
        throw UnsupportedOperationException()
    }

    override suspend fun saveAll(items: List<Repository>): Result<List<Repository>> {
        throw UnsupportedOperationException()
    }

    private suspend fun query(
        query: String,
        sort: RepositorySort?
    ): Result<List<Repository>> {

        // skip api call if query is blank
        if (query.isBlank()) {
            return Result.Success(emptyList())
        }

        // prepare sort
        var sortValue: String? = null
        sort?.let {
            if (it.forks) {
                sortValue = RepositoryApi.SORT_FORKS
                return@let
            }

            if (it.stars) {
                sortValue = RepositoryApi.SORT_STARS
                return@let
            }

            if (it.lastUpdated) {
                sortValue = RepositoryApi.SORT_LAST_UPDATED
                return@let
            }
        }

        val result: Result<RepositoryResponse>
        result = RemoteDataSourceHelper.processGet { repositoryApi.query(query, sortValue) }

        return if (result is Result.Success) {
            Result.Success(result.item.items)
        } else {
            Result.Error(result.error as DataSourceException)
        }
    }
}

interface RepositoryApi {

    @GET(Constant.HTTP.ENDPOINT_REPOSITORY_SEARCH)
    suspend fun query(
        @retrofit2.http.Query("q") query: String,
        @retrofit2.http.Query("sort") sort: String?
    ): Response<RepositoryResponse>

    @GET(Constant.HTTP.ENDPOINT_USER_REPO)
    suspend fun getOwnerRepo(
        @Path("ownerLogin") ownerLogin: String,
        @Path("repoName") repoName: String
    ): Response<Repository>

    companion object {
        const val SORT_STARS = "stars"
        const val SORT_FORKS = "forks"
        const val SORT_LAST_UPDATED = "updated"
    }
}

data class RepositoryResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<Repository>
)
