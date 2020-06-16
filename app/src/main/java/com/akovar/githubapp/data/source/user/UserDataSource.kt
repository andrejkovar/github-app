package com.akovar.githubapp.data.source.user

import com.akovar.githubapp.data.model.User
import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.Result

/**
 * Created by akovar on 13/06/2020.
 */
interface UserDataSource : DataSource<User, String> {

    suspend fun getMe(): Result<User>
}