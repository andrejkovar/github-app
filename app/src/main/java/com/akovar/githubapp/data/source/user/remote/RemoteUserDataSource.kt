package com.akovar.githubapp.data.source.user.remote

import com.akovar.githubapp.data.Constant
import com.akovar.githubapp.data.model.User
import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.RemoteDataSourceHelper
import com.akovar.githubapp.data.source.Result
import com.akovar.githubapp.data.source.user.UserById
import com.akovar.githubapp.data.source.user.UserId
import com.akovar.githubapp.data.source.user.UserMe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by akovar on 10/06/2020.
 */
class RemoteUserDataSource(private val userApi: UserApi) : DataSource<User, UserId> {

    override suspend fun getById(id: UserId): Result<User> {
        return RemoteDataSourceHelper.processGet {
            when (id) {
                is UserMe -> userApi.getMe()
                is UserById -> userApi.getUser(id.id)
            }
        }
    }

    override suspend fun getAll(): Result<List<User>> {
        throw UnsupportedOperationException()
    }

    override suspend fun save(item: User): Result<User> {
        throw UnsupportedOperationException()
    }

    override suspend fun saveAll(items: List<User>): Result<List<User>> {
        throw UnsupportedOperationException()
    }
}

interface UserApi {

    @GET(Constant.HTTP.ENDPOINT_USER_ME)
    suspend fun getMe(): Response<User>

    @GET(Constant.HTTP.ENDPOINT_USERS)
    suspend fun getUser(@Path("login") login: String): Response<User>
}