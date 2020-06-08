package com.ag04.githubapp.components.search

import com.ag04.githubapp.components.base.BaseFragment
import com.ag04.githubapp.components.base.BaseToolbarActivity

class SearchActivity :
    BaseToolbarActivity<SearchContract.View, SearchContract.Presenter>() {

    override fun provideFragment(): BaseFragment<SearchContract.View, SearchContract.Presenter> {
        TODO("Not yet implemented")
    }
}