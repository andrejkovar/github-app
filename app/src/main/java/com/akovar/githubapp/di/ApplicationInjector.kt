package com.akovar.githubapp.di

import com.akovar.githubapp.BuildConfig
import com.akovar.githubapp.client.*
import com.akovar.githubapp.data.source.repository.remote.RepositoryApi
import com.akovar.githubapp.data.source.user.remote.UserApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by akovar on 12/06/2020.
 */
class ApplicationInjector {

    companion object {

        private val clientInstance: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .build()
        }

        private val gitHubAuthClientInstance: AuthClient<GitHubToken, GitHubCredentials> by lazy {
            GitHubAuthClient(gitHubAuthServiceApiInstance, clientInstance)
        }

        private val retrofitInstance: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(clientInstance)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private val gitHubAuthServiceApiInstance: GitHubAuthService by lazy {
            retrofitInstance.create(GitHubAuthService::class.java)
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