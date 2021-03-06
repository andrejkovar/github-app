package com.akovar.githubapp.components.landing

import com.akovar.githubapp.components.base.BaseContract

/**
 * Created by akovar on 15/06/2020.
 */
interface LandingContract {

    interface View : BaseContract.View {

        /**
         * Navigates to login screen.
         */
        fun navigateToLogin()

        /**
         * Navigates to home screen.
         */
        fun navigateToHome()
    }

    interface Presenter : BaseContract.Presenter<View> {

        /**
         * View calls this when user presses login action view.
         */
        fun onLoginClick()

        /**
         * View calls this when user presses skip login action view.
         */
        fun onSkipLoginClick()
    }
}