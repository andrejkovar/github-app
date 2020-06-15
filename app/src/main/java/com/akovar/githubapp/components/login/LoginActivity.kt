package com.akovar.githubapp.components.login

import com.akovar.githubapp.components.base.BaseActivity
import com.akovar.githubapp.components.base.BaseFragment
import com.akovar.githubapp.di.FragmentInjector

/**
 * Created by akovar on 15/06/2020.
 */
class LoginActivity : BaseActivity<LoginContract.View, LoginContract.Presenter>() {

    private val loginFragment: LoginFragment = FragmentInjector.provideLoginFragment()

    override fun provideFragment(): BaseFragment<LoginContract.View, LoginContract.Presenter> {
        return loginFragment
    }
}