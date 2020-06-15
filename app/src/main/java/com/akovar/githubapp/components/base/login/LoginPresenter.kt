package com.akovar.githubapp.components.base.login

import com.akovar.githubapp.client.AuthClient
import com.akovar.githubapp.client.GitHubCredentials
import com.akovar.githubapp.client.GitHubToken
import com.akovar.githubapp.components.base.BasePresenter

/**
 * Created by akovar on 15/06/2020.
 */
class LoginPresenter(
    private val authClient: AuthClient<GitHubToken, GitHubCredentials>
) : BasePresenter<LoginContract.View>(), LoginContract.Presenter