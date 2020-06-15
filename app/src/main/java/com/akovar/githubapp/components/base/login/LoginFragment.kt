package com.akovar.githubapp.components.base.login

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import com.akovar.githubapp.components.base.BaseFragment
import com.akovar.githubapp.databinding.FragmentLoginBinding
import com.akovar.githubapp.di.PresenterInjector

/**
 * Created by akovar on 15/06/2020.
 */
class LoginFragment :
    BaseFragment<LoginContract.View, LoginContract.Presenter>(),
    LoginContract.View {

    private val presenter: LoginContract.Presenter = PresenterInjector.provideLoginPresenter()

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
}