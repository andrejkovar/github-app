package com.ag04.githubapp.components.repositorylist

import com.ag04.githubapp.components.base.BaseFragment
import com.ag04.githubapp.components.base.BaseToolbarActivity
import com.ag04.githubapp.di.FragmentInjector

class RepositoryListActivity :
    BaseToolbarActivity<RepositoryListContract.View, RepositoryListContract.Presenter>() {

    private val repositoryListFragment: RepositoryListFragment =
        FragmentInjector.provideRepositoryListFragment()

    override fun provideFragment(): BaseFragment<RepositoryListContract.View, RepositoryListContract.Presenter> {
        return repositoryListFragment
    }
}