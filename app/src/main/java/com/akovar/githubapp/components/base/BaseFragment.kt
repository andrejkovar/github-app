package com.akovar.githubapp.components.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.akovar.githubapp.R

/**
 * Created by akovar on 08/06/2020.
 */
abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>>
    : Fragment()
    , BaseContract.View {

    /**
     * Root view container holder.
     */
    protected var container: ViewGroup? = null

    /**
     * Used to make sure that view is ready to be used.
     */
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

    /**
     * Destroy all view bindings in this method implementation.
     * ViewBindings are heavy objects and you don't need to keep them
     * in memory when Fragment.onDestroyView() method is called.
     *
     * Your binding objects should live only between onCreateView and onDestroyView.
     */
    protected abstract fun destroyViewBinding()

    /**
     * In case there is a need for additional implementation
     * when view is created, just override this!
     *
     * @param view the newly created and inflated view
     */
    protected open fun onPostViewCreate(view: View) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val resourceView = provideResourceView(inflater)
        this.container = resourceView.findViewById(R.id.container)

        onGlobalLayoutListener = OnGlobalLayoutListener {
            resourceView.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener)
            onGlobalLayoutListener = null

            onPostViewCreate(resourceView)
            providePresenter().onViewReady()
        }

        resourceView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)

        return resourceView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        destroyViewBinding()
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

    override fun onError(errorStatusCode: Int) {
        Toast.makeText(context, R.string.error_unknown_error, Toast.LENGTH_SHORT).show()
    }

    override fun close() {
        activity?.finish()
    }
}