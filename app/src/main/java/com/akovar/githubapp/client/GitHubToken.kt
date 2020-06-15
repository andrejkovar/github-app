package com.akovar.githubapp.client

/**
 * Created by akovar on 15/06/2020.
 */
class GitHubToken(private val accessToken: String) : AuthToken() {

    override fun getToken(): String {
        return accessToken
    }
}