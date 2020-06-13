package com.ag04.githubapp.components.user

import com.ag04.githubapp.components.base.details.BaseDetailsContract

/**
 * Created by akovar on 08/06/2020.
 */
interface UserContract {

    interface View<T> : BaseDetailsContract.View<T> {

    }

    interface Presenter<T> : BaseDetailsContract.Presenter<T, View<T>> {

    }
}