package com.akovar.githubapp.components.base.searchlist

import com.akovar.githubapp.components.base.list.BaseListContract

/**
 * Created by akovar on 09/06/2020.
 */
interface BaseSearchListContract {

    interface View<T> : BaseListContract.View<T> {

    }

    interface Presenter<T, V : View<T>> : BaseListContract.Presenter<T, V> {

        /**
         * Invoked when user clicks on the search action view. It informs
         * presenter that search is active.
         */
        fun onSearchOpened()

        /**
         * Invoked when user close search. It informs presenter
         * that search is not active.
         */
        fun onSearchClosed()

        /**
         * Invoked on text query changes inside search or on submit search.
         *
         * @param input searched input entered by user.
         */
        fun onSearchInputChange(input: String)

        /**
         * Invoked when user submitted query.
         *
         * @param query searched string entered by user.
         */
        fun onSearchSubmit(query: String)
    }
}