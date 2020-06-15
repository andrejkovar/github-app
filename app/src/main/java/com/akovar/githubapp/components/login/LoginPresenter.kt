package com.akovar.githubapp.components.login

import com.akovar.githubapp.components.base.BasePresenter
import timber.log.Timber

/**
 * Created by akovar on 15/06/2020.
 */
class LoginPresenter :
    BasePresenter<LoginContract.View>(),
    LoginContract.Presenter {

    override fun onLoginClick() {
        Timber.d("onLoginClick")
        view?.navigateToLogin()
    }

    override fun onSkipLoginClick() {
        Timber.d("onSkipLoginClick")
        view?.let {
            it.navigateToHome()
            it.close()
        }
    }
}