package com.ag04.githubapp.components.repository

import android.content.Context
import android.content.Intent
import com.ag04.githubapp.components.base.BaseFragment
import com.ag04.githubapp.components.base.BaseToolbarActivity
import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.di.FragmentInjector

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryActivity :
    BaseToolbarActivity<RepositoryContract.View<Repository>,
            RepositoryContract.Presenter<Repository, RepositoryContract.View<Repository>>>() {

    private val repositoryFragment: RepositoryFragment =
        FragmentInjector.provideRepositoryFragment()

    override fun provideFragment(): BaseFragment<RepositoryContract.View<Repository>,
            RepositoryContract.Presenter<Repository, RepositoryContract.View<Repository>>> {
        return repositoryFragment
    }

    override fun isDisplayHomeAsUpEnabled(): Boolean {
        return true
    }

    companion object {

        const val REPOSITORY_PAIR_ID_EXTRA =
            "com.ag04.githubapp.components.repository.REPOSITORY_PAIR_ID_EXTRA"

        /**
         * Opens RepositoryActivity.
         *
         * @param context context
         * @param userLogin user login
         * @param repoName repository name
         */
        fun open(context: Context?, repositoryPairId: RepositoryPairId) {
            context?.let {
                it.startActivity(Intent(context, RepositoryActivity::class.java).apply {
                    putExtra(REPOSITORY_PAIR_ID_EXTRA, repositoryPairId)
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                })
            }
        }
    }
}