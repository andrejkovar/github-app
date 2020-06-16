package com.akovar.githubapp.client.base

import com.akovar.githubapp.client.AuthClient
import com.akovar.githubapp.client.AuthToken
import com.akovar.githubapp.client.Credentials
import com.akovar.githubapp.data.source.Result
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import timber.log.Timber

/**
 * Created by akovar on 15/06/2020.
 */
abstract class BaseAuthClient<T : AuthToken?, C : Credentials?>(val okHttpClient: OkHttpClient) :
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

    private var tokenHandler: AuthClient.TokenHandler<T, C>? = null

    protected val scope = MainScope()

    abstract suspend fun provideTokenFor(credentials: C): Result<T>

    override fun hasToken(): Boolean {
        return token != null
    }

    override fun resetToken() {
        token = null
    }

    override fun authenticate(credentials: C) {
        Timber.d("authenticate with $credentials")

        scope.launch {
            val result = provideTokenFor(credentials)
            onAuthenticateResult(result)
        }
    }

    protected fun onAuthenticateResult(result: Result<T>) {
        Timber.d("onNewTokenResult $result")

        if (result is Result.Success<T>) {
            onAuthenticateSuccess(result)
        } else {
            onAuthenticateError(result as Result.Error<T>)
        }
    }

    protected open fun onAuthenticateSuccess(success: Result.Success<T>) {
        Timber.d("onAuthenticateSuccess $success")

        token = success.item
        tokenHandler?.onNewToken(this, token!!)
    }

    protected open fun onAuthenticateError(error: Result.Error<T>) {
        Timber.d("onAuthenticateError $error")

        resetToken()
        tokenHandler?.onFailed(this, error.error)
    }

    override fun setTokenHandler(tokenHandler: AuthClient.TokenHandler<T, C>?) {
        this.tokenHandler = tokenHandler
    }
}
