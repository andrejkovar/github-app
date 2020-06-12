package com.ag04.githubapp.components.base.searchlist

import com.ag04.githubapp.components.base.list.BaseListPresenter
import com.ag04.githubapp.data.source.Result
import kotlinx.coroutines.cancel
import timber.log.Timber

/**
 * Created by akovar on 09/06/2020.
 */
abstract class BaseSearchListPresenter<T, V : BaseSearchListContract.View<T>> :
    BaseListPresenter<T, V>(),
    BaseSearchListContract.Presenter<T, V> {

    protected var query: String = ""
    protected var isQueryMode = false

    abstract suspend fun provideQueryItems(): Result<List<T>>

    override suspend fun provideItems(): Result<List<T>> {
        return provideQueryItems()
    }

    override fun onSearchOpened() {
        Timber.d("onSearchOpened")
        isQueryMode = true
    }

    override fun onSearchClosed() {
        Timber.d("onSearchClosed")
        isQueryMode = false
    }

    override fun onSearchInputChange(input: String) {
        Timber.d("onSearchInputChange: $input")
        query = input
    }

    override fun onSearchSubmit(query: String) {
        Timber.d("onSearchSubmit: $query")
        loadQuery()
    }

    private fun loadQuery() {
        if (isQueryMode) {
            super.load()
        }
    }

    private fun cancelQuery() {
        scope.cancel()
    }
}