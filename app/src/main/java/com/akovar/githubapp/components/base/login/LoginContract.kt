package com.akovar.githubapp.components.base.login

import android.net.Uri
import com.akovar.githubapp.components.base.BaseContract

/**
 * Created by akovar on 15/06/2020.
 */
interface LoginContract {

    interface View : BaseContract.View {

        fun provideIntentUri(): Uri?

        fun openGitHubLoginWeb(uri: Uri)

        fun navigateToHome()

        fun navigateBackToLanding()
    }

    interface Presenter : BaseContract.Presenter<View>
}