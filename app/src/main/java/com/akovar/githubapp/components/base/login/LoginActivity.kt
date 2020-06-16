package com.akovar.githubapp.components.base.login

import android.content.Context
import android.content.Intent
import com.akovar.githubapp.components.base.BaseActivity
import com.akovar.githubapp.components.base.BaseFragment
import com.akovar.githubapp.di.FragmentProvider

/**
 * Created by akovar on 15/06/2020.
 */
class LoginActivity : BaseActivity<LoginContract.View, LoginContract.Presenter>() {

    private val loginFragment: LoginFragment = FragmentProvider.provideLoginFragment()

    override fun provideFragment(): BaseFragment<LoginContract.View, LoginContract.Presenter> {
        return loginFragment
    }

    companion object {

        /**
         * Opens LoginActivity.
         *
         * @param context context
         */
        fun open(context: Context?) {
            context?.let {
                it.startActivity(Intent(context, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                })
            }
        }
    }
}