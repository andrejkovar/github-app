package com.akovar.githubapp.components.base

/**
 * Created by akovar on 08/06/2020.
 */
interface BaseContract {

    interface View {

        /**
         * Invoked when there is an error.
         *
         * @param errorStatusCode the error status code
         */
        fun onError(errorStatusCode: Int)
    }

    /**
     * This base presenter is aware of view lifecycle.
     */
    interface Presenter<V : View> {

        /**
         * This method sets view inside of presenter.
         *
         * @param view view instance
         */
        fun onView(view: V)

        /**
         * Invoked when default view state should be implemented.
         */
        fun onViewReady()

        /**
         * View should call this method to notify presenter
         * that view is started.
         */
        fun onStart()

        /**
         * View should call this method to notify presenter
         * that view is resumed.
         */
        fun onResume()

        /**
         * View should call this method to notify presenter
         * that view is paused.
         */
        fun onPause()

        /**
         * View should call this method to notify presenter
         * that view is stopped.
         */
        fun onStop()

        /**
         * View should call this method to notify presenter
         * that view is destroyed.
         */
        fun onDestroy()
    }
}