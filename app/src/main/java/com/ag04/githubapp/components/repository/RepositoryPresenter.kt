package com.ag04.githubapp.components.repository

import android.os.Parcelable
import com.ag04.githubapp.components.base.details.BaseDetailsPresenter
import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.source.Result
import com.ag04.githubapp.data.source.repository.RepositoryDataSource
import kotlinx.android.parcel.Parcelize

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryPresenter(
    private val repositoryPairId: RepositoryPairId,
    private val repositoryDataSource: RepositoryDataSource
) : BaseDetailsPresenter<
        Repository,
        RepositoryPairId,
        RepositoryContract.View<Repository>>(repositoryPairId),
    RepositoryContract.Presenter<Repository, RepositoryContract.View<Repository>> {

    override suspend fun provideItemResult(): Result<Repository> {
        return repositoryDataSource.getUserRepository(
            repositoryPairId.userLogin,
            repositoryPairId.repoName
        )
    }

    override fun onRepositoryOwnerClick() {
        item?.let {
            view?.navigateToUserDetails(it.user)
        }
    }
}

@Parcelize
data class RepositoryPairId(val userLogin: String, val repoName: String) : Parcelable