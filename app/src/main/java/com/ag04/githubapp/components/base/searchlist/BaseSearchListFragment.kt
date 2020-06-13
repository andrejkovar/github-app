package com.ag04.githubapp.components.base.searchlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import com.ag04.githubapp.R
import com.ag04.githubapp.components.base.list.BaseListFragment

/**
 * Created by akovar on 09/06/2020.
 */
abstract class BaseSearchListFragment<T, V : BaseSearchListContract.View<T>, P : BaseSearchListContract.Presenter<T, V>>() :
    BaseListFragment<T, V, P>(),
    BaseSearchListContract.View<T> {

    /**
     * Search menu item holder.
     */
    protected var searchMenuItem: MenuItem? = null

    /**
     * Provides search hint resource id.
     *
     * @return search hint resource id
     */
    @StringRes
    protected open fun provideSearchHintResourceId(): Int {
        return R.string.label_search
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        searchMenuItem = menu.findItem(R.id.action_search).apply {
            setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    providePresenter().onSearchOpened()
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    providePresenter().onSearchClosed()
                    return true
                }
            })

            (actionView as SearchView).apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        providePresenter().onSearchSubmit(query ?: "")
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        providePresenter().onSearchInputChange(newText ?: "")
                        return true
                    }
                })

                maxWidth = Int.MAX_VALUE
                queryHint = getString(provideSearchHintResourceId())
            }
        }

        super.onCreateOptionsMenu(menu, inflater)
    }
}