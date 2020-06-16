package com.akovar.githubapp.components.user

import android.content.Context
import android.content.Intent
import com.akovar.githubapp.R
import com.akovar.githubapp.components.base.BaseFragment
import com.akovar.githubapp.components.base.BaseToolbarActivity
import com.akovar.githubapp.data.model.User
import com.akovar.githubapp.di.FragmentProvider

/**
 * Created by akovar on 08/06/2020.
 */
class UserActivity :
    BaseToolbarActivity<UserContract.View<User>, UserContract.Presenter<User>>() {

    /**
     * User fragment holder.
     */
    private val userFragment: UserFragment = FragmentProvider.provideUserFragment()

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

        const val USER_ID_EXTRA = "com.akovar.githubapp.components.user.USER_ID_EXTRA"

        /**
         * Opens UserActivity.
         *
         * @param context context
         * @param userId  user id
         */
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