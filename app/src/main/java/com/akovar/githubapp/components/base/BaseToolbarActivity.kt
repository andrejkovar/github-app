package com.akovar.githubapp.components.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import com.akovar.githubapp.R

/**
 * Created by akovar on 08/06/2020.
 */
abstract class BaseToolbarActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> :
    BaseActivity<V, P>() {

    protected var toolbar: Toolbar? = null

    /**
     * Provides toolbar title resource id.
     *
     * @return toolbar title resource id
     */
    @StringRes
    protected open fun provideToolbarTitleResourceId(): Int = R.string.app_name

    /**
     * Returns if 'home' should be enabled.
     *
     * @return enable 'home'
     */
    protected open fun isDisplayHomeAsUpEnabled(): Boolean {
        return false
    }

    override fun provideLayoutResourceId(): Int {
        return R.layout.activity_base_toolbar
    }

    /**
     * Provides toolbar id.
     *
     * @return toolbar id
     */
    protected open fun provideToolbarId(): Int {
        return R.id.toolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar = findViewById<Toolbar>(provideToolbarId())?.apply {
            setSupportActionBar(this)
        }

        supportActionBar?.apply {
            setTitle(provideToolbarTitleResourceId())
            setDisplayHomeAsUpEnabled(isDisplayHomeAsUpEnabled())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}