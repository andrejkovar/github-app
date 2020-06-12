package com.ag04.githubapp.components.repository

import android.content.Context
import android.content.Intent
import com.ag04.githubapp.components.base.BaseFragment
import com.ag04.githubapp.components.base.BaseToolbarActivity
import com.ag04.githubapp.di.FragmentInjector

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryActivity :
    BaseToolbarActivity<RepositoryContract.View, RepositoryContract.Presenter>() {

    private val repositoryFragment: RepositoryFragment =
        FragmentInjector.provideRepositoryFragment()

    override fun provideFragment(): BaseFragment<RepositoryContract.View, RepositoryContract.Presenter> {
        return repositoryFragment
    }

    override fun isDisplayHomeAsUpEnabled(): Boolean {
        return true
    }

    companion object {

        const val USER_LOGIN_EXTRA =
            "com.ag04.githubapp.components.repository.USER_LOGIN_EXTRA"
        const val REPO_NAME_EXTRA =
            "com.ag04.githubapp.components.repository.REPO_NAME_EXTRA"

        /**
         * Opens RepositoryActivity.
         *
         * @param context context
         * @param userLogin user login
         * @param repoName repository name
         */
        fun open(context: Context?, userLogin: String, repoName: String) {
            context?.let {
                it.startActivity(Intent(context, RepositoryActivity::class.java).apply {
                    putExtra(USER_LOGIN_EXTRA, userLogin)
                    putExtra(REPO_NAME_EXTRA, repoName)
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                })
            }
        }
    }
}