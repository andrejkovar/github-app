package com.akovar.githubapp.client

import okhttp3.Interceptor

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
interface AuthClient<T : AuthToken?, C : Credentials?> {

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

    fun token(): T?

    /**
     * Callback interface to be invoke when a new token is fetched.
     *
     * @param <T> AuthToken
     * @param <C> Credentials
    </C></T> */
    interface NewTokenHandler<T : AuthToken?, C : Credentials?> {
        /**
         * Invoke when there is a new [AuthToken].
         *
         * @param client the caller of this method
         * @param token  the new token
         * @return true if token is accepted or false otherwise
         */
        fun accept(client: AuthClient<T, C>, token: T): Boolean
    }
//
//    /**
//     * Callback interface to be invoke when a new token is fetched.
//     *
//     * @param <T> AuthToken
//     * @param <C> Credentials
//    </C></T> */
//    interface OnTokenExpiredAction<T : AuthToken?, C : Credentials?> {
//        /**
//         * Invoke when the [AuthToken] has expired.
//         *
//         * @param client the caller of this method
//         * @param token  the expired token
//         */
//        fun action(client: AuthClient<T, C>, token: T)
//    }
}