package com.ag04.githubapp.data.source.user.remote

import com.ag04.githubapp.data.model.User
import com.ag04.githubapp.data.source.DataSourceError
import com.ag04.githubapp.data.source.DataSourceException
import com.ag04.githubapp.data.source.Result
import com.ag04.githubapp.data.source.user.UserDataSource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException

/**
 * Created by akovar on 10/06/2020.
 */
class RemoteUserDataSource(
    private val userApi: UserApi
) : UserDataSource {

    override suspend fun getById(id: String): Result<User> {
        val response: Response<User>
        try {
            response = userApi.getUser(id)
        } catch (exception: IOException) {
            return Result.Error(
                DataSourceException(
                    DataSourceError.CODE_IO_ERROR,
                    "Get user failed",
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
                    "Get user response unsuccessful",
                    Exception(response.toString())
                )
            )
        }
    }

    override suspend fun getAll(): Result<List<User>> {
        throw UnsupportedOperationException()
    }

    override suspend fun save(item: User): Result<User> {
        throw UnsupportedOperationException()
    }

    override suspend fun saveAll(item: List<User>): Result<List<User>> {
        throw UnsupportedOperationException()
    }
}

interface UserApi {

    @GET("/users/{login}")
    suspend fun getUser(@Path("login") login: String): Response<User>
}