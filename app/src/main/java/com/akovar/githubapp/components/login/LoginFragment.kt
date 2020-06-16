package com.akovar.githubapp.components.login

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import com.akovar.githubapp.components.base.BaseFragment
import com.akovar.githubapp.components.landing.LandingActivity
import com.akovar.githubapp.components.repositorylist.RepositoryListActivity
import com.akovar.githubapp.databinding.FragmentLoginBinding
import com.akovar.githubapp.di.PresenterProvider

/**
 * Created by akovar on 15/06/2020.
 */
class LoginFragment :
    BaseFragment<LoginContract.View, LoginContract.Presenter>(),
    LoginContract.View {

    private val presenter: LoginContract.Presenter = PresenterProvider.provideLoginPresenter()

    private lateinit var binding: FragmentLoginBinding

    override fun provideResourceView(inflater: LayoutInflater): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun providePresenter(): LoginContract.Presenter {
        return presenter
    }

    override fun provideIntentUri(): Uri? {
        return activity?.intent?.data
    }

    override fun openGitHubLoginWeb(uri: Uri) {
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    override fun navigateToHome() {
        RepositoryListActivity.open(context)
    }

    override fun navigateBackToLanding() {
        LandingActivity.open(context)
    }
}