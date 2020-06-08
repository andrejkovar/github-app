package com.ag04.githubapp.components.repository

import com.ag04.githubapp.components.base.BaseFragment

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryFragment :
    BaseFragment<RepositoryContract.View, RepositoryContract.Presenter>(),
    RepositoryContract.View {

    override fun provideResourceViewId(): Int {
        TODO("Not yet implemented")
    }

    override fun providePresenter(): RepositoryContract.Presenter {
        TODO("Not yet implemented")
    }
}