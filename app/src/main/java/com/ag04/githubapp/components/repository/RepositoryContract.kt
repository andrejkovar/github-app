package com.ag04.githubapp.components.repository

import com.ag04.githubapp.components.base.BaseContract
import com.ag04.githubapp.data.model.Repository

/**
 * Created by akovar on 08/06/2020.
 */
interface RepositoryContract {

    interface View : BaseContract.View {

        /**
         * Invoked by presenter to show/hide loading indicator.
         *
         * @param show show loading indicator
         */
        fun showLoadingIndicator(show: Boolean)

        /**
         * Invoked by presenter to show/hide
         * repository details.
         *
         * @param show show details
         */
        fun showDetails(show: Boolean)

        /**
         * Invoked by presenter when repository data is loaded
         * and ready to display.
         *
         * @param repository repository data
         */
        fun onRepositoryLoaded(repository: Repository)
    }

    interface Presenter : BaseContract.Presenter<View> {

        /**
         * Invoked when user triggers refresh view.
         */
        fun onRefresh()

        /**
         * Invoked when user click on owner details view item.
         */
        fun onRepositoryOwnerClick()
    }
}