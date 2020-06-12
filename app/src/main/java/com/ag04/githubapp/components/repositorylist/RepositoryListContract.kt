package com.ag04.githubapp.components.repositorylist

import com.ag04.githubapp.components.base.adapter.BaseRecyclerViewAdapter
import com.ag04.githubapp.components.base.searchlist.BaseSearchListContract
import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.model.User

/**
 * Created by akovar on 08/06/2020.
 */
interface RepositoryListContract {

    interface View : BaseSearchListContract.View<Repository> {

        /**
         * Invoked by presenter to show sort dialog.
         *
         * @param sort repository search sort
         */
        fun showSortDialog(sort: RepositorySearchSort)

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
    }

    interface Presenter : BaseSearchListContract.Presenter<Repository, View> {

        /**
         * Invoked when user press on sort action view.
         */
        fun onSortClick()

        /**
         * Invoked when user press on sort submit action view.
         */
        fun onSortSubmit(sort: RepositorySearchSort)

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