package com.akovar.githubapp.components.landing

import android.content.Context
import android.content.Intent
import com.akovar.githubapp.components.base.BaseActivity
import com.akovar.githubapp.components.base.BaseFragment
import com.akovar.githubapp.di.FragmentInjector

/**
 * Created by akovar on 15/06/2020.
 */
class LandingActivity : BaseActivity<LandingContract.View, LandingContract.Presenter>() {

    private val landingFragment: LandingFragment = FragmentInjector.provideLandingFragment()

    override fun provideFragment(): BaseFragment<LandingContract.View, LandingContract.Presenter> {
        return landingFragment
    }

    /**
     * Opens LandingActivity.
     *
     * @param context context
     */
    fun open(context: Context?) {
        context?.let {
            it.startActivity(Intent(context, LandingActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }
    }
}