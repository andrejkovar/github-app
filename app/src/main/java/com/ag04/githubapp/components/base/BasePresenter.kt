package com.ag04.githubapp.components.base

/**
 * Created by akovar on 08/06/2020.
 */
open class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    protected var view: V? = null

    override fun onView(view: V) {
        this.view = view
    }

    override fun onViewReady() {
    }

    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
        view = null
    }
}