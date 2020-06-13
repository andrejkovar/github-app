package com.ag04.githubapp.components.repositorylist

import com.ag04.githubapp.components.base.searchlist.BaseSearchListPresenter
import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.source.Result
import com.ag04.githubapp.data.source.repository.RepositoryDataSource
import com.ag04.githubapp.data.source.repository.RepositorySort
import timber.log.Timber

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryListPresenter(
    private val repositoryDataSource: RepositoryDataSource
) : BaseSearchListPresenter<Repository, RepositoryListContract.View>(),
    RepositoryListContract.Presenter {

    private val sort = RepositorySearchSort(stars = false, forks = false, updated = false)

    override fun onItemClick(item: Repository) {
        super.onItemClick(item)
        view?.navigateToRepositoryDetails(item)
    }

    override fun onOwnerAvatarClick(item: Repository) {
        Timber.d("onOwnerAvatarClick: ${item.id}")
        view?.navigateToUserDetails(item.user)
    }

    override fun onSortClick() {
        Timber.d("onSortClick")
        view?.showSortDialog(sort)
    }

    override fun onSortSubmit(sort: RepositorySearchSort) {
        this.sort.apply {
            stars = sort.stars
            forks = sort.forks
            updated = sort.updated
        }

        load()
    }

    override suspend fun provideQueryItems(): Result<List<Repository>> {
        return repositoryDataSource.query(
            query,
            RepositorySort(sort.stars, sort.forks, sort.updated)
        )
    }
}