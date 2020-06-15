package com.akovar.githubapp.components.landing

import com.akovar.githubapp.components.base.BasePresenter
import timber.log.Timber

/**
 * Created by akovar on 15/06/2020.
 */
class LandingPresenter :
    BasePresenter<LandingContract.View>(),
    LandingContract.Presenter {

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