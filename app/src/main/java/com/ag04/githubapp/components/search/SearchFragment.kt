package com.ag04.githubapp.components.search

import com.ag04.githubapp.components.base.BaseFragment

/**
 * Created by akovar on 08/06/2020.
 */
class SearchFragment :
    BaseFragment<SearchContract.View, SearchContract.Presenter>(),
    SearchContract.View {

    override fun provideResourceViewId(): Int {
        TODO("Not yet implemented")
    }

    override fun providePresenter(): SearchContract.Presenter {
        TODO("Not yet implemented")
    }
}