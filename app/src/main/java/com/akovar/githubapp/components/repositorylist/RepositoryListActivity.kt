package com.akovar.githubapp.components.repositorylist

import com.akovar.githubapp.components.base.BaseFragment
import com.akovar.githubapp.components.base.BaseToolbarActivity
import com.akovar.githubapp.di.FragmentInjector

class RepositoryListActivity :
    BaseToolbarActivity<RepositoryListContract.View, RepositoryListContract.Presenter>() {

    /**
     * Repository list fragment holder.
     */
    private val repositoryListFragment: RepositoryListFragment =
        FragmentInjector.provideRepositoryListFragment()

    override fun provideFragment(): BaseFragment<RepositoryListContract.View, RepositoryListContract.Presenter> {
        return repositoryListFragment
    }
}