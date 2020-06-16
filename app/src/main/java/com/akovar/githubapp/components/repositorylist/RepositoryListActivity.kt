package com.akovar.githubapp.components.repositorylist

import android.content.Context
import android.content.Intent
import com.akovar.githubapp.components.base.BaseFragment
import com.akovar.githubapp.components.base.BaseToolbarActivity
import com.akovar.githubapp.di.FragmentProvider

class RepositoryListActivity :
    BaseToolbarActivity<RepositoryListContract.View, RepositoryListContract.Presenter>() {

    /**
     * Repository list fragment holder.
     */
    private val repositoryListFragment: RepositoryListFragment =
        FragmentProvider.provideRepositoryListFragment()

    override fun provideFragment(): BaseFragment<RepositoryListContract.View, RepositoryListContract.Presenter> {
        return repositoryListFragment
    }

    companion object {

        /**
         * Opens RepositoryListActivity.
         *
         * @param context context
         */
        fun open(context: Context?) {
            context?.let {
                it.startActivity(Intent(it, RepositoryListActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
    }
}