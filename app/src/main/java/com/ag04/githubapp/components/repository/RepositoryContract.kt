package com.ag04.githubapp.components.repository

import com.ag04.githubapp.components.base.details.BaseDetailsContract
import com.ag04.githubapp.data.model.User

/**
 * Created by akovar on 08/06/2020.
 */
interface RepositoryContract {

    interface View<T> : BaseDetailsContract.View<T> {

        /**
         * Invoked by presenter to navigate to user
         * details screen.
         *
         * @param user user
         */
        fun navigateToUserDetails(user: User)
    }

    interface Presenter<T, V : View<T>> : BaseDetailsContract.Presenter<T, V> {

        /**
         * Invoked when user click on owner details view item.
         */
        fun onRepositoryOwnerClick()
    }
}