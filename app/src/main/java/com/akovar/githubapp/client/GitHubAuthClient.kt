package com.akovar.githubapp.client

import com.akovar.githubapp.BuildConfig
import com.akovar.githubapp.data.source.Result
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST


/**
 * Created by akovar on 15/06/2020.
 */
class GitHubAuthClient(
    private val gitHubAuthService: GitHubAuthService,
    okHttpClient: OkHttpClient
) :
    BaseAuthClient<GitHubToken, GitHubCredentials>(okHttpClient),
    AuthClient<GitHubToken, GitHubCredentials> {

    override fun resetToken() {
        TODO("Not yet implemented")
    }

    override suspend fun provideTokenFor(credentials: GitHubCredentials): Result<GitHubToken> {
        val response = gitHubAuthService.authWithCode(
            BuildConfig.GITHUB_CLIENT_ID,
            BuildConfig.GITHUB_CLIENT_SECRET,
            credentials.code
        )

        return if (response.isSuccessful) {
            Result.Success(GitHubToken(response.body()!!.accessToken))
        } else {
            Result.Error(Exception("${response.errorBody()}"))
        }
    }

}

interface GitHubAuthService {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    suspend fun authWithCode(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): Response<AuthResponse>
}

data class AuthResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("token_type")
    val tokenType: String
)