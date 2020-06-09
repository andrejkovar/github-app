package com.ag04.githubapp.components.base.list

import com.ag04.githubapp.components.base.BasePresenter
import timber.log.Timber

/**
 * Created by akovar on 09/06/2020.
 */
abstract class BaseListPresenter<T, V : BaseListContract.View<T>> :
    BasePresenter<V>(),
    BaseListContract.Presenter<T, V> {

    protected var items: List<T>? = null

    override fun onRefresh() {
        Timber.d("onRefresh")
        load()
    }

    override fun onItemClick(item: T) {
        Timber.d("onItemClick: $item")
    }

    private fun load() {
        view?.setItems(emptyList())
        view?.showNoResults(items.isNullOrEmpty())
        view?.showLoadingProgress(false)
    }
}