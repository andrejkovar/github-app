package com.ag04.githubapp.components.repositorylist

import com.ag04.githubapp.components.base.BaseFragment
import com.ag04.githubapp.components.base.BaseToolbarActivity
import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.di.FragmentInjector

class RepositoryListActivity :
    BaseToolbarActivity<RepositoryListContract.View<Repository>, RepositoryListContract.Presenter<Repository>>() {

    private val repositoryListFragment: RepositoryListFragment =
        FragmentInjector.provideRepositoryListFragment()

    override fun provideFragment(): BaseFragment<RepositoryListContract.View<Repository>, RepositoryListContract.Presenter<Repository>> {
        return repositoryListFragment
    }
}