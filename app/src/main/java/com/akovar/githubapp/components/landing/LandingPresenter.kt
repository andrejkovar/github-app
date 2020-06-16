package com.akovar.githubapp.components.landing

import com.akovar.githubapp.client.AuthClient
import com.akovar.githubapp.client.github.GitHubCredentials
import com.akovar.githubapp.client.github.GitHubToken
import com.akovar.githubapp.components.base.BasePresenter
import timber.log.Timber

/**
 * Created by akovar on 15/06/2020.
 */
class LandingPresenter(
    private val authClient: AuthClient<GitHubToken, GitHubCredentials>
) :
    BasePresenter<LandingContract.View>(),
    LandingContract.Presenter {

    override fun onLoginClick() {
        Timber.d("onLoginClick")

        if (authClient.hasToken()) {
            authClient.resetToken()
        }

        view?.navigateToLogin()
    }

    override fun onSkipLoginClick() {
        Timber.d("onSkipLoginClick")

        if (authClient.hasToken()) {
            authClient.resetToken()
        }

        view?.apply {
            navigateToHome()
            close()
        }
    }
}