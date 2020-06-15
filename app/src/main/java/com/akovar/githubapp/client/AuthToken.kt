package com.akovar.githubapp.client

/**
 * Created by akovar on 15/06/2020.
 */
abstract class AuthToken {

    /**
     * Get the token.
     *
     * @return the token and it should be non null
     */
    abstract fun getToken(): String
}