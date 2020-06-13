package com.ag04.githubapp.components.base.details

import com.ag04.githubapp.components.base.BasePresenter
import com.ag04.githubapp.data.source.Result
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

    protected val scope = MainScope()
    protected var item: T? = null

    abstract suspend fun provideItemResult(): Result<T>

    override fun onViewReady() {
        super.onViewReady()
        loadItem(itemId)
    }

    override fun onRefresh() {
        loadItem(itemId)
    }

    protected open fun loadItem(itemId: ID) {
        Timber.d("loadItem $itemId")

        scope.launch {
            view?.showLoadingProgress(true)
            view?.showDetails(false)

            val result = provideItemResult()
            onItemLoaded(result)
        }
    }

    protected open fun onItemLoaded(result: Result<T>) {
        Timber.d("onItemLoaded $result")

        if (result is Result.Success) {
            item = result.item
            view?.apply {
                setItem(item!!)
                showDetails(true)
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