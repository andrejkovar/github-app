package com.akovar.githubapp.components.base.list

import com.akovar.githubapp.components.base.BasePresenter
import com.akovar.githubapp.data.source.Result
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by akovar on 09/06/2020.
 */
abstract class BaseListPresenter<T, V : BaseListContract.View<T>> :
    BasePresenter<V>(),
    BaseListContract.Presenter<T, V> {

    /**
     * Coroutines scope used to load items list.
     */
    protected val scope = MainScope()

    /**
     * Items list holder.
     */
    protected var items: List<T>? = null

    /**
     * Provides items list result. This is suspend function because
     * item will probably come from some data source which
     * will be blocking function.
     *
     * @return items list result
     */
    abstract suspend fun provideItemsResult(): Result<List<T>>

    override fun onViewReady() {
        super.onViewReady()
        load()
    }

    override fun onRefresh() {
        Timber.d("onRefresh")
        load()
    }

    override fun onItemClick(item: T) {
        Timber.d("onItemClick: $item")
    }

    /**
     * Loads items list and notifies view about result.
     */
    protected open fun load() {
        Timber.d("onLoad")

        scope.launch {
            view?.showLoadingProgress(true)

            val result = provideItemsResult()
            onLoaded(result)
        }
    }

    /**
     * Invoked when items list result has been loaded.
     *
     * @param result items list result
     */
    protected fun onLoaded(result: Result<List<T>>) {
        Timber.d("onLoaded $result")

        if (result is Result.Success) {
            items = result.item
            view?.let {
                view?.setItems(items)
                view?.showNoResults(items.isNullOrEmpty())
            }
        } else {
            view?.onError((result as Result.Error).error.code)
        }

        view?.showLoadingProgress(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}