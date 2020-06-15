package com.akovar.githubapp.client

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

    protected val scope = MainScope()

    abstract suspend fun provideTokenFor(credentials: C): Result<T>

    override fun hasToken(): Boolean {
        return token != null
    }

    override fun token(): T? {
        return token
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
    }

    protected open fun onAuthenticateError(error: Result.Error<T>) {
        Timber.d("onAuthenticateError $error")
        token = null
    }
}
