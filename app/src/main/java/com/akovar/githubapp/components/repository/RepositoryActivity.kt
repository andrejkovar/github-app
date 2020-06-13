package com.akovar.githubapp.components.repository

import android.content.Context
import android.content.Intent
import com.akovar.githubapp.R
import com.akovar.githubapp.components.base.BaseFragment
import com.akovar.githubapp.components.base.BaseToolbarActivity
import com.akovar.githubapp.data.model.Repository
import org.koin.android.ext.android.get

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryActivity :
    BaseToolbarActivity<RepositoryContract.View<Repository>,
            RepositoryContract.Presenter<Repository, RepositoryContract.View<Repository>>>() {

    /**
     * Repository fragment holder.
     */
    private val repositoryFragment: RepositoryFragment = get()

    override fun provideFragment(): BaseFragment<RepositoryContract.View<Repository>,
            RepositoryContract.Presenter<Repository, RepositoryContract.View<Repository>>> {
        return repositoryFragment
    }

    override fun isDisplayHomeAsUpEnabled(): Boolean {
        return true
    }

    override fun provideToolbarTitleResourceId(): Int {
        return R.string.title_repository_details
    }

    companion object {

        const val REPOSITORY_PAIR_ID_EXTRA =
            "com.akovar.githubapp.components.repository.REPOSITORY_PAIR_ID_EXTRA"

        /**
         * Opens RepositoryActivity.
         *
         * @param context context
         * @param repositoryPairId repository pair id
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