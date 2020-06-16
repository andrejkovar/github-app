package com.akovar.githubapp.components.login

import android.net.Uri
import com.akovar.githubapp.BuildConfig
import com.akovar.githubapp.client.AuthClient
import com.akovar.githubapp.client.github.GitHubCredentials
import com.akovar.githubapp.client.github.GitHubToken
import com.akovar.githubapp.components.base.BasePresenter

/**
 * Created by akovar on 15/06/2020.
 */
class LoginPresenter(
    private val authClient: AuthClient<GitHubToken, GitHubCredentials>
) : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    /**
     * Current credentials holder.
     */
    private var credentials: GitHubCredentials? = null

    /**
     * On token handler holder.
     */
    private val onTokenHandler =
        object : AuthClient.TokenHandler<GitHubToken, GitHubCredentials> {

            override fun onNewToken(
                client: AuthClient<GitHubToken, GitHubCredentials>,
                token: GitHubToken
            ) {
                view?.apply {
                    navigateToHome()
                    close()
                }
            }

            override fun onFailed(
                client: AuthClient<GitHubToken, GitHubCredentials>,
                throwable: Throwable
            ) {
                view?.apply {
                    onError(0)
                    navigateBackToLanding()
                }
            }
        }

    init {
        authClient.setTokenHandler(onTokenHandler)
    }

    override fun onResume() {
        super.onResume()

        val uri = view?.provideIntentUri()
        if (uri != null && uri.toString().startsWith(BuildConfig.GITHUB_REDIRECT_URL)) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                onCode(code)
            }
        } else {
            startAuth()
        }
    }

    /**
     * Starts with authorisation process.
     */
    private fun startAuth() {
        view?.openGitHubLoginWeb(
            Uri.parse(
                BuildConfig.GITHUB_AUTH_URL +
                        "?client_id=" + BuildConfig.GITHUB_CLIENT_ID +
                        "&redirect_uri=" + BuildConfig.GITHUB_REDIRECT_URL
            )
        )
    }

    /**
     * Called when code is received from GitHub authorisation service.
     *
     * @param code received code
     */
    private fun onCode(code: String) {
        credentials = GitHubCredentials(code)
        authClient.authenticate(credentials!!)
    }
}