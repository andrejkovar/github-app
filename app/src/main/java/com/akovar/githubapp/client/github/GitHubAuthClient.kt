package com.akovar.githubapp.client.github

import com.akovar.githubapp.BuildConfig
import com.akovar.githubapp.client.AuthClient
import com.akovar.githubapp.client.base.BaseAuthClient
import com.akovar.githubapp.data.Constant
import com.akovar.githubapp.data.source.Result
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST


/**
 * Created by akovar on 15/06/2020.
 */
class GitHubAuthClient(
    private val gitHubAuthService: GitHubAuthService
) :
    BaseAuthClient<GitHubToken, GitHubCredentials>(),
    AuthClient<GitHubToken, GitHubCredentials> {

    /**
     * Auth client holder.
     */
    private val authClient = OkHttpClient.Builder()
        .addInterceptor(this)
        .build()

    companion object {

        /**
         * List of endpoints for which you authorisation is required (token in header).
         */
        private val needAuthPathsList: List<String> = listOf(
            Constant.HTTP.ENDPOINT_USER_ME
        )
    }

    override suspend fun provideTokenFor(credentials: GitHubCredentials): Result<GitHubToken> {
        val response = gitHubAuthService.authWithCode(
            BuildConfig.GITHUB_CLIENT_ID,
            BuildConfig.GITHUB_CLIENT_SECRET,
            credentials.code
        )

        return if (response.isSuccessful) {
            Result.Success(
                GitHubToken(
                    response.body()!!.accessToken
                )
            )
        } else {
            Result.Error(Exception("${response.errorBody()}"))
        }
    }

    override fun logout() {
        // Not supported, user should revoke it manually or delete cookies from device
    }

    override fun needToken(request: Request): Boolean {
        val uri = request.url.toUri().toString()
        return needAuthPathsList.any { needAuthPathsList ->
            uri.endsWith(needAuthPathsList)
        }
    }

    override fun provideTokenHeader(): Pair<String, String> {
        return Pair("Authorization", "token ${token?.getToken()}")
    }

    override fun client(): OkHttpClient {
        return authClient
    }
}

interface GitHubAuthService {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("/login/oauth/access_token")
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