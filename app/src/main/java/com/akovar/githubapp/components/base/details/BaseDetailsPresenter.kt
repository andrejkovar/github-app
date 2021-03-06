package com.akovar.githubapp.components.base.details

import com.akovar.githubapp.components.base.BasePresenter
import com.akovar.githubapp.data.source.DataSourceException
import com.akovar.githubapp.data.source.Result
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by akovar on 13/06/2020.
 */
abstract class BaseDetailsPresenter<T, ID, V : BaseDetailsContract.View<T>>(
    protected val itemId: ID
) :
    BaseDetailsContract.Presenter<T, V>,
    BasePresenter<V>() {

    /**
     * Coroutines scope used to load item details.
     */
    protected val scope = MainScope()

    /**
     * Item details holder.
     */
    protected var item: T? = null

    /**
     * Provides item result. This is suspend function because
     * item will probably come from some data source which
     * will be blocking function.
     *
     * @return item result
     */
    abstract suspend fun provideItemResult(): Result<T>

    override fun onViewReady() {
        super.onViewReady()
        loadItem(itemId)
    }

    override fun onRefresh() {
        loadItem(itemId)
    }

    /**
     * Loads item and notifies view about item result.
     *
     * @param itemId item id
     */
    protected open fun loadItem(itemId: ID) {
        Timber.d("loadItem $itemId")

        scope.launch {
            view?.let {
                it.showLoadingProgress(true)
                it.showDetails(false)
            }

            val result = provideItemResult()
            onItemLoaded(result)
        }
    }

    /**
     * Invoked when item result has been loaded.
     *
     * @param result item result
     */
    protected open fun onItemLoaded(result: Result<T>) {
        Timber.d("onItemLoaded $result")

        if (result is Result.Success) {
            item = result.item
            view?.let {
                it.setItem(item!!)
                it.showDetails(true)
            }
        } else {
            view?.onError((result.error as DataSourceException).code)
        }

        view?.showLoadingProgress(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}