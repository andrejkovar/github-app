package com.akovar.githubapp.di

import com.akovar.githubapp.BuildConfig
import com.akovar.githubapp.client.AuthClient
import com.akovar.githubapp.client.github.GitHubAuthClient
import com.akovar.githubapp.client.github.GitHubAuthService
import com.akovar.githubapp.client.github.GitHubCredentials
import com.akovar.githubapp.client.github.GitHubToken
import com.akovar.githubapp.data.source.repository.remote.RepositoryApi
import com.akovar.githubapp.data.source.user.remote.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by akovar on 12/06/2020.
 */
class ApplicationProvider {

    companion object {

        private val clientRetrofitInstance: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_AUTH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private val gitHubAuthServiceApiInstance: GitHubAuthService =
            clientRetrofitInstance.create(GitHubAuthService::class.java)

        private val gitHubAuthClientInstance: AuthClient<GitHubToken, GitHubCredentials> =
            GitHubAuthClient(gitHubAuthServiceApiInstance)

        private val retrofitInstance: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(gitHubAuthClientInstance.client())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private val repositoryApiInstance: RepositoryApi by lazy {
            retrofitInstance.create(RepositoryApi::class.java)
        }

        private val userApiInstance: UserApi by lazy {
            retrofitInstance.create(UserApi::class.java)
        }

        fun provideRepositoryApi(): RepositoryApi {
            return repositoryApiInstance
        }

        fun provideUserApi(): UserApi {
            return retrofitInstance.create(UserApi::class.java)
        }

        fun provideGitHubAuthServiceApi(): GitHubAuthService {
            return gitHubAuthServiceApiInstance
        }

        fun provideRetrofit(): Retrofit {
            return retrofitInstance
        }

        fun provideAuthClient(): AuthClient<GitHubToken, GitHubCredentials> {
            return gitHubAuthClientInstance
        }
    }
}