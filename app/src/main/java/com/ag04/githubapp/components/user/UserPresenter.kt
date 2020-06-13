package com.ag04.githubapp.components.user

import com.ag04.githubapp.components.base.details.BaseDetailsPresenter
import com.ag04.githubapp.data.model.User
import com.ag04.githubapp.data.source.Result
import com.ag04.githubapp.data.source.user.UserDataSource

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