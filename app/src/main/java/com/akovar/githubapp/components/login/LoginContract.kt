package com.akovar.githubapp.components.login

import android.net.Uri
import com.akovar.githubapp.components.base.BaseContract

/**
 * Created by akovar on 15/06/2020.
 */
interface LoginContract {

    interface View : BaseContract.View {

        /**
         * Provides Intent uri if it is available, null otherwise.
         *
         * @return uri
         */
        fun provideIntentUri(): Uri?

        /**
         * Opens GitHub login web on provided uri.
         *
         * @param uri GitHub login web uri
         */
        fun openGitHubLoginWeb(uri: Uri)

        /**
         * Navigates to home screen.
         */
        fun navigateToHome()

        /**
         * Navigates back to Landing screen.
         */
        fun navigateBackToLanding()
    }

    interface Presenter : BaseContract.Presenter<View>
}