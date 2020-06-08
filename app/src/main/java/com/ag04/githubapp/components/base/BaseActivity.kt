package com.ag04.githubapp.components.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.ag04.githubapp.R

/**
 * Created by akovar on 08/06/2020.
 */
abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> :
    AppCompatActivity() {

    companion object {
        private const val BASE_ACTIVITY_CONTAINER_TAG = "BASE_ACTIVITY_CONTAINER_TAG"
    }

    /**
     * Provides fragment which will be inserted inside of
     * activity container.
     *
     * @return fragment
     */
    protected abstract fun provideFragment(): BaseFragment<V, P>

    /**
     * Provides activity layout resource id.
     *
     * @return resource id
     */
    protected open fun provideLayoutResourceId(): Int {
        return R.layout.activity_base
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(provideLayoutResourceId())

        if (savedInstanceState == null) {
            val fragment: BaseFragment<V, P> = provideFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment, BASE_ACTIVITY_CONTAINER_TAG)
                .commit()
        }
    }
}