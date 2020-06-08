package com.ag04.githubapp.components.repository

import com.ag04.githubapp.components.base.BaseFragment
import com.ag04.githubapp.components.base.BaseToolbarActivity

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryActivity :
    BaseToolbarActivity<RepositoryContract.View, RepositoryContract.Presenter>() {

    override fun provideFragment(): BaseFragment<RepositoryContract.View, RepositoryContract.Presenter> {
        TODO("Not yet implemented")
    }
}