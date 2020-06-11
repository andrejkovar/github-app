package com.ag04.githubapp.components.repositorylist

import com.ag04.githubapp.components.base.searchlist.BaseSearchListPresenter
import com.ag04.githubapp.data.model.Repository
import timber.log.Timber

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryListPresenter :
    BaseSearchListPresenter<Repository, SearchContract.View<Repository>>(),
    SearchContract.Presenter<Repository> {

    private val sort = RepositorySearchSort(stars = false, forks = false, updated = false)

    override fun onItemClick(item: Repository) {
        super.onItemClick(item)
        // TODO navigate to Repository details screen
    }

    override fun onItemImageClick(item: Repository) {
        Timber.d("onItemImageClick: ${item.id}")
        // TODO navigate to User details screen
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
    }

    override suspend fun provideItems(): List<Repository> {
        return listOf(Repository(), Repository(), Repository(), Repository(), Repository())
    }
}