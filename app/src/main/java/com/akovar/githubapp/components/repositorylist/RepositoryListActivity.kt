package com.akovar.githubapp.components.repositorylist

import com.akovar.githubapp.components.base.BaseFragment
import com.akovar.githubapp.components.base.BaseToolbarActivity
import org.koin.android.ext.android.inject

class RepositoryListActivity :
    BaseToolbarActivity<RepositoryListContract.View, RepositoryListContract.Presenter>() {

    /**
     * Repository list fragment holder.
     */
    private val repositoryListFragment: RepositoryListFragment by inject()

    override fun provideFragment(): BaseFragment<RepositoryListContract.View, RepositoryListContract.Presenter> {
        return repositoryListFragment
    }
}