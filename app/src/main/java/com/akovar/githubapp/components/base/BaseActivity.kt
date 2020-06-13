package com.akovar.githubapp.components.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akovar.githubapp.R

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutResourceId())

        if (savedInstanceState == null) {
            val fragment: BaseFragment<V, P> = provideFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_container, fragment, BASE_ACTIVITY_CONTAINER_TAG)
                .commit()
        }
    }
}