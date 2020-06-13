package com.akovar.githubapp.components.base

import timber.log.Timber

/**
 * Created by akovar on 08/06/2020.
 */
open class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    /**
     * View holder.
     */
    protected var view: V? = null

    override fun onView(view: V) {
        Timber.d("onView: $view")
        this.view = view
    }

    override fun onViewReady() {
        Timber.d("onViewReady")
    }

    override fun onStart() {
        Timber.d("onStart")
    }

    override fun onResume() {
        Timber.d("onResume")
    }

    override fun onPause() {
        Timber.d("onPause")
    }

    override fun onStop() {
        Timber.d("onStop")
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        view = null
    }
}