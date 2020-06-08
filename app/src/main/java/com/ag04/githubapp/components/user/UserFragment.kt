package com.ag04.githubapp.components.user

import com.ag04.githubapp.components.base.BaseFragment

/**
 * Created by akovar on 08/06/2020.
 */
class UserFragment :
    BaseFragment<UserContract.View, UserContract.Presenter>(),
    UserContract.View {

    override fun provideResourceViewId(): Int {
        TODO("Not yet implemented")
    }

    override fun providePresenter(): UserContract.Presenter {
        TODO("Not yet implemented")
    }
}