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

    private val scope = MainScope()

    protected var items: List<T>? = null

    abstract suspend fun provideItems(): Result<List<T>>

    override fun onRefresh() {
        Timber.d("onRefresh")
        load()
    }

    override fun onItemClick(item: T) {
        Timber.d("onItemClick: $item")
    }

    private fun load() {
        scope.launch {
            view?.showLoadingProgress(true)

            val result = provideItems()
            if (result is Result.Success) {
                Timber.d("onLoaded ${result.item}")
                items = result.item
                view?.setItems(items)
                view?.showNoResults(items.isNullOrEmpty())
            } else {
                Timber.d("onLoad failed ${result.error}")
                view?.onError(0)
            }

            view?.showLoadingProgress(false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}