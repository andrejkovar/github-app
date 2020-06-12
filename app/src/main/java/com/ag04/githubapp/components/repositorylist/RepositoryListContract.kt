package com.ag04.githubapp.components.repositorylist

import com.ag04.githubapp.components.base.adapter.BaseRecyclerViewAdapter
import com.ag04.githubapp.components.base.searchlist.BaseSearchListContract
import com.ag04.githubapp.data.model.Repository

/**
 * Created by akovar on 08/06/2020.
 */
interface RepositoryListContract {

    interface View<T> : BaseSearchListContract.View<T> {

        fun showSortDialog(sort: RepositorySearchSort)
    }

    interface Presenter<T> : BaseSearchListContract.Presenter<T, View<T>> {

        fun onSortClick()

        fun onSortSubmit(sort: RepositorySearchSort)

        fun onItemImageClick(item: T)
    }
}

data class RepositorySearchSort(
    var stars: Boolean,
    var forks: Boolean,
    var updated: Boolean
)

interface OnRepositoryClickListener : BaseRecyclerViewAdapter.OnItemClickListener<Repository> {

    fun onAvatarClick(item: Repository)
}