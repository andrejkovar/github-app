package com.ag04.githubapp.components.user

import android.content.Context
import android.content.Intent
import com.ag04.githubapp.R
import com.ag04.githubapp.components.base.BaseFragment
import com.ag04.githubapp.components.base.BaseToolbarActivity
import com.ag04.githubapp.data.model.User
import com.ag04.githubapp.di.FragmentInjector

/**
 * Created by akovar on 08/06/2020.
 */
class UserActivity :
    BaseToolbarActivity<UserContract.View<User>, UserContract.Presenter<User>>() {

    private val userFragment: UserFragment = FragmentInjector.provideUserFragment()

    override fun provideFragment(): BaseFragment<UserContract.View<User>, UserContract.Presenter<User>> {
        return userFragment
    }

    override fun isDisplayHomeAsUpEnabled(): Boolean {
        return true
    }

    override fun provideToolbarTitleResourceId(): Int {
        return R.string.title_user_details
    }

    companion object {

        const val USER_ID_EXTRA = "com.ag04.githubapp.components.user.USER_ID_EXTRA"

        fun open(context: Context?, userId: String) {
            context?.let {
                it.startActivity(Intent(it, UserActivity::class.java).apply {
                    putExtra(USER_ID_EXTRA, userId)
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                })
            }
        }
    }
}