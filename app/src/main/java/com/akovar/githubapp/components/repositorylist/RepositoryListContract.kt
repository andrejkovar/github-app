package com.akovar.githubapp.components.repositorylist

import com.akovar.githubapp.components.base.adapter.BaseRecyclerViewAdapter
import com.akovar.githubapp.components.base.searchlist.BaseSearchListContract
import com.akovar.githubapp.data.model.Repository
import com.akovar.githubapp.data.model.User
import com.akovar.githubapp.data.source.repository.RepositorySort

/**
 * Created by akovar on 08/06/2020.
 */
interface RepositoryListContract {

    interface View : BaseSearchListContract.View<Repository> {

        /**
         * Invoked by presenter to show user profile.
         *
         * @param show show user profile
         */
        fun showUserProfile(show: Boolean)

        /**
         * Invoked by presenter to show sort dialog.
         *
         * @param sort repository search sort
         */
        fun showSortDialog(sort: RepositorySort)

        /**
         * Invoked by presenter to navigate to repository
         * details screen.
         *
         * @param repo repository
         */
        fun navigateToRepositoryDetails(repo: Repository)

        /**
         * Invoked by presenter to navigate to user
         * details screen.
         *
         * @param user user
         */
        fun navigateToUserDetails(user: User)

        /**
         * Invoked by presenter to navigate to my profile
         * details screen.
         */
        fun navigateToMyProfileDetails()
    }

    interface Presenter : BaseSearchListContract.Presenter<Repository, View> {

        /**
         * Invoked when user press on my profile action view.
         */
        fun onMyProfileClick()

        /**
         * Invoked when user press on sort action view.
         */
        fun onSortClick()

        /**
         * Invoked when user press on sort submit action view.
         */
        fun onSortSubmit(sort: RepositorySort)

        /**
         * Invoked when user press on owner avatar action view.
         */
        fun onOwnerAvatarClick(item: Repository)
    }
}

data class RepositorySearchSort(
    var stars: Boolean,
    var forks: Boolean,
    var updated: Boolean
)

interface OnRepositoryClickListener : BaseRecyclerViewAdapter.OnItemClickListener<Repository> {

    /**
     * Invoked when user press on owner avatar action view.
     */
    fun onAvatarClick(item: Repository)
}