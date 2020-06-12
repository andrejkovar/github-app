package com.ag04.githubapp.components.base.list

import com.ag04.githubapp.components.base.BasePresenter
import com.ag04.githubapp.data.source.Result
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

    protected val scope = MainScope()

    protected var items: List<T>? = null

    abstract suspend fun provideItems(): Result<List<T>>

    override fun onRefresh() {
        Timber.d("onRefresh")
        load()
    }

    override fun onItemClick(item: T) {
        Timber.d("onItemClick: $item")
    }

    protected open fun load() {
        Timber.d("onLoad")

        scope.launch {
            view?.showLoadingProgress(true)

            val result = provideItems()
            onLoaded(result)

            view?.showLoadingProgress(false)
        }
    }

    protected fun onLoaded(result: Result<List<T>>) {
        Timber.d("onLoaded $result")

        if (result is Result.Success) {
            items = result.item
            view?.setItems(items)
            view?.showNoResults(items.isNullOrEmpty())
        } else {
            view?.onError(0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}