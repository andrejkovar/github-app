package com.ag04.githubapp.components.user

import com.ag04.githubapp.components.base.BaseFragment
import com.ag04.githubapp.components.base.BaseToolbarActivity

/**
 * Created by akovar on 08/06/2020.
 */
class UserActivity :
    BaseToolbarActivity<UserContract.View, UserContract.Presenter>() {

    override fun provideFragment(): BaseFragment<UserContract.View, UserContract.Presenter> {
        TODO("Not yet implemented")
    }
}