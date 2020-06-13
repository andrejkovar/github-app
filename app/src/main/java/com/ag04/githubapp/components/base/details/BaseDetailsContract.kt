package com.ag04.githubapp.components.base.details

import com.ag04.githubapp.components.base.BaseContract

/**
 * Created by akovar on 13/06/2020.
 */
interface BaseDetailsContract {

    interface View<T> : BaseContract.View {

        /**
         * Invoked by presenter to show/hide loading indicator.
         *
         * @param show show loading indicator
         */
        fun showLoadingProgress(show: Boolean)

        /**
         * Invoked by presenter to show/hide
         * item details.
         *
         * @param show show item details
         */
        fun showDetails(show: Boolean)

        /**
         * Invoked by presenter when item data is loaded
         * and ready to display.
         *
         * @param item item data
         */
        fun setItem(item: T)
    }

    interface Presenter<T, V : View<T>> : BaseContract.Presenter<V> {

        /**
         * View calls this when on refresh action is triggered.
         */
        fun onRefresh()
    }
}