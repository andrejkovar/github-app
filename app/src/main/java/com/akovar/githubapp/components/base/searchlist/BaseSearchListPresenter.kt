package com.akovar.githubapp.components.base.searchlist

import com.akovar.githubapp.components.base.list.BaseListPresenter
import com.akovar.githubapp.data.source.Result
import timber.log.Timber

/**
 * Created by akovar on 09/06/2020.
 */
abstract class BaseSearchListPresenter<T, V : BaseSearchListContract.View<T>> :
    BaseListPresenter<T, V>(),
    BaseSearchListContract.Presenter<T, V> {

    /**
     * Current entered query holder.
     */
    protected var query: String = ""

    /**
     * Current query mode holder. True if search is opened,
     * false otherwise.i
     */
    protected var isQueryMode = false

    /**
     * Provides query items list result. This is suspend function because
     * item will probably come from some data source which
     * will be blocking function.
     *
     * @return query items list result
     */
    abstract suspend fun provideQueryItemsResult(): Result<List<T>>

    override suspend fun provideItemsResult(): Result<List<T>> {
        return provideQueryItemsResult()
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

    /**
     * Loads items from current query. It checks if components
     * is in query mode and only then, load is executed.
     */
    protected fun loadQuery() {
        if (isQueryMode) {
            super.load()
        }
    }
}