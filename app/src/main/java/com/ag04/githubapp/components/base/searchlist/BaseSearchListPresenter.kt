package com.ag04.githubapp.components.base.searchlist

import com.ag04.githubapp.components.base.list.BaseListPresenter
import timber.log.Timber

/**
 * Created by akovar on 09/06/2020.
 */
abstract class BaseSearchListPresenter<T, V : BaseSearchListContract.View<T>> :
    BaseListPresenter<T, V>(),
    BaseSearchListContract.Presenter<T, V> {

    protected var query: String = ""

    override fun onSearchOpened() {
        Timber.d("onSearchOpened")
    }

    override fun onSearchClosed() {
        Timber.d("onSearchClosed")
    }

    override fun onSearchInputChange(input: String) {
        Timber.d("onSearchInputChange: $input")
        query = input
    }

    override fun onSearchSubmit(query: String) {
        Timber.d("onSearchSubmit: $query")
    }
}