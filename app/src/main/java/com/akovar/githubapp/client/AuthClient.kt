package com.akovar.githubapp.client

import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 * AuthClient interface which define the contract to handle authentication
 * with the provided [Credentials].
 *
 *
 * [AuthClient] extends [Interceptor] in order to intercept http request and
 * add the authorization token in the header.
 *
 *
 * Created by akovar on 15/06/2020.
 */
interface AuthClient<T : AuthToken?, C : Credentials?> : Interceptor {

    /**
     * Check if the instance has a [AuthToken].
     *
     * @return true if token is present or false otherwise
     */
    fun hasToken(): Boolean

    /**
     * Clear the current token if available.
     */
    fun resetToken()

    /**
     * Authenticate with provided credentials.
     *
     * @param credentials credentials
     */
    fun authenticate(credentials: C)

    /**
     * Logout.
     */
    fun logout()

    /**
     * Provides internal client.
     *
     * @return client.
     */
    fun client(): OkHttpClient

    /**
     * Callback interface to be invoke when a new token is fetched.
     *
     * @param <T> AuthToken
     * @param <C> Credentials
    </C></T> */
    interface TokenHandler<T : AuthToken?, C : Credentials?> {

        /**
         * Invoked when there is a new [AuthToken].
         *
         * @param client the caller of this method
         * @param token  the new token
         */
        fun onNewToken(client: AuthClient<T, C>, token: T)

        /**
         * Invoked when authentication went wrong.
         *
         * @param client the caller of this method
         * @param throwable reason
         */
        fun onFailed(client: AuthClient<T, C>, throwable: Throwable)
    }

    /**
     * Sets token handler to current AuthClient.
     *
     * @param tokenHandler token handler
     */
    fun setTokenHandler(tokenHandler: TokenHandler<T, C>?)
}