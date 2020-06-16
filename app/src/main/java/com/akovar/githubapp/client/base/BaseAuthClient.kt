package com.akovar.githubapp.client.base

import com.akovar.githubapp.client.AuthClient
import com.akovar.githubapp.client.AuthToken
import com.akovar.githubapp.client.Credentials
import com.akovar.githubapp.data.source.Result
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber

/**
 * Created by akovar on 15/06/2020.
 */
abstract class BaseAuthClient<T : AuthToken?, C : Credentials?> :
    AuthClient<T, C> {

    /**
     * Get the current [AuthToken].
     *
     * @return token if available or null otherwise
     */
    var token: T? = null

    /**
     * Get the current [credentials].
     *
     * @return token if available or null otherwise
     */
    var credentials: C? = null

    /**
     * Current token handler holder.
     */
    private var tokenHandler: AuthClient.TokenHandler<T, C>? = null

    /**
     * Auth coroutine scope holder.
     */
    protected val authScope = MainScope()

    /**
     * Provides result with token for provided credentials.
     *
     * @param credentials credentials
     * @return result with token
     */
    abstract suspend fun provideTokenFor(credentials: C): Result<T>

    /**
     * Checks if provided request need token header.
     *
     * @param request request to check
     * @return true if need token header, false otherwise
     */
    abstract fun needToken(request: Request): Boolean

    /**
     * Provides token header pair with auth header name and its value.
     *
     * @return pair pair with auth header name and its value
     */
    abstract fun provideTokenHeader(): Pair<String, String>

    override fun hasToken(): Boolean {
        Timber.d("hasToken ${token != null}")
        return token != null
    }

    override fun resetToken() {
        Timber.d("resetToken")
        token = null
    }

    override fun authenticate(credentials: C) {
        Timber.d("authenticate with $credentials")

        authScope.launch {
            val result = provideTokenFor(credentials)
            onAuthenticateResult(result)
        }
    }

    override fun setTokenHandler(tokenHandler: AuthClient.TokenHandler<T, C>?) {
        this.tokenHandler = tokenHandler
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (needToken(request)) {
            val tokenHeader = provideTokenHeader()
            request = request.newBuilder()
                .addHeader(tokenHeader.first, tokenHeader.second)
                .build()
        }

        return chain.proceed(request)
    }

    private fun onAuthenticateResult(result: Result<T>) {
        Timber.d("onNewTokenResult $result")

        if (result is Result.Success<T>) {
            onAuthenticateSuccess(result)
        } else {
            onAuthenticateError(result as Result.Error<T>)
        }
    }

    /**
     * Called when authentication is success and it returns its result.
     *
     * @param success success result
     */
    protected open fun onAuthenticateSuccess(success: Result.Success<T>) {
        Timber.d("onAuthenticateSuccess $success")

        token = success.item
        tokenHandler?.onNewToken(this, token!!)
    }

    /**
     * Called when authentication failed and it returns its result.
     *
     * @param error error result
     */
    protected open fun onAuthenticateError(error: Result.Error<T>) {
        Timber.d("onAuthenticateError $error")

        resetToken()
        tokenHandler?.onFailed(this, error.error)
    }
}
