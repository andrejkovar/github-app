package com.akovar.githubapp.components.landing

import android.view.LayoutInflater
import android.view.View
import com.akovar.githubapp.components.base.BaseFragment
import com.akovar.githubapp.components.login.LoginActivity
import com.akovar.githubapp.components.repositorylist.RepositoryListActivity
import com.akovar.githubapp.databinding.FragmentLandingBinding
import com.akovar.githubapp.di.PresenterProvider

/**
 * Created by akovar on 15/06/2020.
 */
class LandingFragment :
    BaseFragment<LandingContract.View, LandingContract.Presenter>(),
    LandingContract.View {

    /**
     * Login presenter holder.
     */
    private val presenter: LandingContract.Presenter = PresenterProvider.provideLandingPresenter()

    /**
     * Layout binding holder for this fragment.
     */
    private lateinit var binding: FragmentLandingBinding

    override fun provideResourceView(inflater: LayoutInflater): View {
        binding = FragmentLandingBinding.inflate(inflater)
        return binding.root
    }

    override fun providePresenter(): LandingContract.Presenter {
        return presenter
    }

    override fun onPostViewCreate(view: View) {
        super.onPostViewCreate(view)

        binding.buttonLogin.setOnClickListener {
            presenter.onLoginClick()
        }

        binding.buttonSkipLogin.setOnClickListener {
            presenter.onSkipLoginClick()
        }
    }

    override fun navigateToLogin() {
        LoginActivity.open(context)
    }

    override fun navigateToHome() {
        RepositoryListActivity.open(context)
    }
}