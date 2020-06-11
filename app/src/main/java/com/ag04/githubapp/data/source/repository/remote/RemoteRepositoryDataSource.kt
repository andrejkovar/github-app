package com.ag04.githubapp.data.source.repository.remote

import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.source.Result
import com.ag04.githubapp.data.source.repository.RepositoryDataSource
import com.ag04.githubapp.data.source.repository.RepositorySort
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException


/**
 * Created by akovar on 10/06/2020.
 */
class RemoteRepositoryDataSource(
    private val repositoryApi: RepositoryApi
) : RepositoryDataSource {

    override suspend fun query(query: String, sort: RepositorySort?): Result<List<Repository>> {
        val response: Response<RepositoryResponse>
        try {
            withContext(Dispatchers.IO) {
                response = repositoryApi.query(
                    query,
                    ""
                ).execute()
            }
        } catch (e: IOException) {
            return Result.Error(e)
        }

        return if (response.isSuccessful) {
            Result.Success(response.body()!!.items)
        } else {
            Result.Error(Exception())
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
    ): Call<RepositoryResponse>
}

data class RepositoryResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<Repository>
)
