package com.ag04.githubapp.components.repositorylist

import com.ag04.githubapp.components.base.BaseFragment
import com.ag04.githubapp.components.base.BaseToolbarActivity
import com.ag04.githubapp.data.model.Repository

class RepositoryListActivity :
    BaseToolbarActivity<SearchContract.View<Repository>, SearchContract.Presenter<Repository>>() {

    private val repositoryListFragment: RepositoryListFragment = RepositoryListFragment()

    override fun provideFragment(): BaseFragment<SearchContract.View<Repository>, SearchContract.Presenter<Repository>> {
        return repositoryListFragment
    }
}