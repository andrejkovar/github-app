package com.ag04.githubapp.components.search

import com.ag04.githubapp.components.base.searchlist.BaseSearchListContract

/**
 * Created by akovar on 08/06/2020.
 */
interface SearchContract {

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