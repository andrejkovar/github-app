package com.ag04.githubapp.components.base.list

import android.view.View
import androidx.annotation.LayoutRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ag04.githubapp.R
import com.ag04.githubapp.components.base.BaseFragment

/**
 * Created by akovar on 09/06/2020.
 */
abstract class BaseListFragment<T, V : BaseListContract.View<T>, P : BaseListContract.Presenter<T, V>> :
    BaseFragment<V, P>(),
    BaseListContract.View<T> {

    /**
     * Empty state view holder.
     */
    protected var emptyStateView: View? = null

    /**
     * Swipe to refresh view holder.
     */
    protected var swipeRefreshLayout: SwipeRefreshLayout? = null

    /**
     * Invoked to get the NoResult layout id to be inflate on {@link #emptyStateView} and
     * then shown to the user when there is no items (items list is empty).
     *
     * @return the id of the no result layout
     */
    @LayoutRes
    open fun provideNoResultsLayout(): Int {
        return R.layout.layout_default_no_results
    }

    /**
     * Setups recycler view.
     */
    abstract fun initRecyclerView()

    override fun onPostViewCreate(view: View) {
        super.onPostViewCreate(view)

        emptyStateView = layoutInflater.inflate(provideNoResultsLayout(), container, false)
        container?.addView(emptyStateView)
        emptyStateView?.visibility = View.GONE

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout?.setOnRefreshListener {
            providePresenter().onRefresh()
        }

        initRecyclerView()
    }

    override fun showLoadingProgress(show: Boolean) {
        swipeRefreshLayout?.isRefreshing = show
    }

    override fun showNoResults(show: Boolean) {
        emptyStateView?.visibility = if (show) View.VISIBLE else View.GONE
    }
}