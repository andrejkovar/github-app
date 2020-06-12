package com.ag04.githubapp.data.source.repository.remote

import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.source.DataSourceError
import com.ag04.githubapp.data.source.DataSourceException
import com.ag04.githubapp.data.source.Result
import com.ag04.githubapp.data.source.repository.RepositoryDataSource
import com.ag04.githubapp.data.source.repository.RepositorySort
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.IOException


/**
 * Created by akovar on 10/06/2020.
 */
class RemoteRepositoryDataSource(
    private val repositoryApi: RepositoryApi
) : RepositoryDataSource {

    override suspend fun query(
        query: String,
        sort: RepositorySort?
    ): Result<List<Repository>> {

        // skip api call if query is blank
        if (query.isBlank()) {
            return Result.Success(emptyList())
        }

        val response: Response<RepositoryResponse>
        try {
            response = repositoryApi.query(query, query)
        } catch (exception: IOException) {
            return Result.Error(
                DataSourceException(
                    DataSourceError.CODE_IO_ERROR,
                    "Query failed",
                    exception
                )
            )
        }

        return if (response.isSuccessful) {
            Result.Success(response.body()!!.items)
        } else {
            Result.Error(
                DataSourceException(
                    DataSourceError.CODE_UNSUCCESSFUL_ERROR,
                    "Query response unsuccessful",
                    Exception(response.toString())
                )
            )
        }
    }

    override suspend fun getUserRepository(
        userLogin: String,
        repoName: String
    ): Result<Repository> {
        val response: Response<Repository>
        try {
            response = repositoryApi.getOwnerRepo(userLogin, repoName)
        } catch (exception: IOException) {
            return Result.Error(
                DataSourceException(
                    DataSourceError.CODE_IO_ERROR,
                    "Get user repo failed",
                    exception
                )
            )
        }

        return if (response.isSuccessful) {
            Result.Success(response.body()!!)
        } else {
            Result.Error(
                DataSourceException(
                    DataSourceError.CODE_UNSUCCESSFUL_ERROR,
                    "Get user repo response unsuccessful",
                    Exception(response.toString())
                )
            )
        }
    }

    override suspend fun getById(id: Long): Result<Repository> {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): Result<List<Repository>> {
        TODO("Not yet implemented")
    }

    override suspend fun save(item: Repository): Result<Repository> {
        throw UnsupportedOperationException()
    }

    override suspend fun saveAll(item: List<Repository>): Result<List<Repository>> {
        throw UnsupportedOperationException()
    }
}

interface RepositoryApi {

    @GET("/search/repositories")
    suspend fun query(
        @Query("q") query: String,
        @Query("sort") sort: String
    ): Response<RepositoryResponse>

    @GET("/repos/{ownerLogin}/{repoName}")
    suspend fun getOwnerRepo(
        @Path("ownerLogin") ownerLogin: String,
        @Path("repoName") repoName: String
    ): Response<Repository>
}

data class RepositoryResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<Repository>
)
