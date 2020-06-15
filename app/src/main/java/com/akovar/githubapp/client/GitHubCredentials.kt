package com.akovar.githubapp.client

/**
 * Created by akovar on 15/06/2020.
 */
class GitHubCredentials(val code: String) : Credentials() {

    override fun getCredentials(): String {
        return code
    }
}