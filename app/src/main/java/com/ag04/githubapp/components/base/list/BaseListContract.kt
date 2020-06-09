package com.ag04.githubapp.components.base.list

import com.ag04.githubapp.components.base.BaseContract

/**
 * Created by akovar on 09/06/2020.
 */
interface BaseListContract {

    interface View<T> : BaseContract.View {

        /**
         * Shows/dismisses loading indicator.
         */
        fun showLoadingProgress(show: Boolean)

        /**
         * Shows/hides no results indicator.
         */
        fun showNoResults(show: Boolean)

        /**
         * Sets items to collection.
         */
        fun setItems(items: List<T>?)
    }

    interface Presenter<T, V : View<T>> :
        BaseContract.Presenter<V> {

        /**
         * View calls this when on refresh action is triggered.
         */
        fun onRefresh()

        /**
         * View calls this when item from collection is clicked.
         */
        fun onItemClick(item: T)
    }
}