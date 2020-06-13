package com.akovar.githubapp.components.user

import com.akovar.githubapp.components.base.details.BaseDetailsPresenter
import com.akovar.githubapp.data.model.User
import com.akovar.githubapp.data.source.Result
import com.akovar.githubapp.data.source.user.UserDataSource

/**
 * Created by akovar on 08/06/2020.
 */
class UserPresenter(
    private val userId: String,
    private val userDataSource: UserDataSource
) : BaseDetailsPresenter<User, String, UserContract.View<User>>(userId),
    UserContract.Presenter<User> {

    override suspend fun provideItemResult(): Result<User> {
        return userDataSource.getById(userId)
    }
}