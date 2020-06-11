package com.ag04.githubapp.components.base.list

import com.ag04.githubapp.components.base.BasePresenter
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

    abstract suspend fun provideItems(): List<T>

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

            items = provideItems()

            view?.setItems(items)
            view?.showNoResults(items.isNullOrEmpty())
            view?.showLoadingProgress(false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}