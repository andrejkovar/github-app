package com.ag04.githubapp.components.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.fragment.app.Fragment
import com.ag04.githubapp.R

/**
 * Created by akovar on 08/06/2020.
 */
abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>>
    : Fragment()
    , BaseContract.View {

    protected var container: ViewGroup? = null

    private var onGlobalLayoutListener: OnGlobalLayoutListener? = null

    /**
     * Provides resource layout view for this fragment.
     *
     * @return resource layout view
     */
    protected abstract fun provideResourceView(inflater: LayoutInflater): View

    /**
     * Provides presenter for this fragment.
     *
     * @return presenter
     */
    protected abstract fun providePresenter(): P

    override fun onError(errorStatusCode: Int) {
    }

    /**
     * In case there is a need for additional implementation
     * when view is created, just override this!
     *
     * @param view the newly created and inflated view
     */
    protected open fun onPostViewCreate(view: View) {
        container = view.findViewById(R.id.container)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val resourceView = provideResourceView(inflater)
        onGlobalLayoutListener = OnGlobalLayoutListener {
            resourceView.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener)
            onGlobalLayoutListener = null

            onPostViewCreate(resourceView)
            providePresenter().onViewReady()
        }

        resourceView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)

        return resourceView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        providePresenter().onView(this@BaseFragment as V)
    }

    override fun onStart() {
        super.onStart()
        providePresenter().onStart()
    }

    override fun onResume() {
        super.onResume()
        providePresenter().onResume()
    }

    override fun onPause() {
        super.onPause()
        providePresenter().onPause()
    }

    override fun onStop() {
        super.onStop()
        providePresenter().onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        providePresenter().onDestroy()
    }
}