package com.ag04.githubapp.components.search

import com.ag04.githubapp.components.base.BaseFragment
import com.ag04.githubapp.components.base.BaseToolbarActivity
import com.ag04.githubapp.data.model.Repository

class SearchActivity :
    BaseToolbarActivity<SearchContract.View<Repository>, SearchContract.Presenter<Repository>>() {

    private val searchFragment: SearchFragment = SearchFragment()

    override fun provideFragment(): BaseFragment<SearchContract.View<Repository>, SearchContract.Presenter<Repository>> {
        return searchFragment
    }
}