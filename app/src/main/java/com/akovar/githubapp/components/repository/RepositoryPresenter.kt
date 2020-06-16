package com.akovar.githubapp.components.repository

import android.os.Parcelable
import com.akovar.githubapp.components.base.details.BaseDetailsPresenter
import com.akovar.githubapp.data.model.Repository
import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.Result
import com.akovar.githubapp.data.source.repository.RepositoryId
import com.akovar.githubapp.data.source.repository.UserRepository
import kotlinx.android.parcel.Parcelize

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryPresenter(
    private val repositoryPairId: RepositoryPairId,
    private val repositoryDataSource: DataSource<Repository, RepositoryId>
) : BaseDetailsPresenter<
        Repository,
        RepositoryPairId,
        RepositoryContract.View<Repository>>(repositoryPairId),
    RepositoryContract.Presenter<Repository, RepositoryContract.View<Repository>> {

    override suspend fun provideItemResult(): Result<Repository> {
        return repositoryDataSource.getById(
            UserRepository(
                repositoryPairId.userLogin,
                repositoryPairId.repoName
            )
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