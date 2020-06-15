package com.akovar.githubapp.components.login

import android.view.LayoutInflater
import android.view.View
import com.akovar.githubapp.components.base.BaseFragment
import com.akovar.githubapp.components.repositorylist.RepositoryListActivity
import com.akovar.githubapp.databinding.FragmnetLoginBinding
import com.akovar.githubapp.di.PresenterInjector

/**
 * Created by akovar on 15/06/2020.
 */
class LoginFragment :
    BaseFragment<LoginContract.View, LoginContract.Presenter>(),
    LoginContract.View {

    /**
     * Login presenter holder.
     */
    private val presenter: LoginContract.Presenter = PresenterInjector.provideLoginPresenter()

    /**
     * Layout binding holder for this fragment.
     */
    private lateinit var binding: FragmnetLoginBinding

    override fun provideResourceView(inflater: LayoutInflater): View {
        binding = FragmnetLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun providePresenter(): LoginContract.Presenter {
        return presenter
    }

    override fun onPostViewCreate(view: View) {
        super.onPostViewCreate(view)

        binding.buttonLogin.setOnClickListener {
            presenter.onLoginClick()
        }

        binding.buttonSkipLogin.setOnClickListener {
            presenter.onSkipLoginClick()
        }
    }

    override fun navigateToLogin() {
        // TODO
    }

    override fun navigateToHome() {
        RepositoryListActivity.open(context)
    }
}