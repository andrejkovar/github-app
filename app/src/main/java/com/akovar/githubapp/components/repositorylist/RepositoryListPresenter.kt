package com.akovar.githubapp.components.repositorylist

import com.akovar.githubapp.components.base.searchlist.BaseSearchListPresenter
import com.akovar.githubapp.data.Constant
import com.akovar.githubapp.data.model.Repository
import com.akovar.githubapp.data.source.Result
import com.akovar.githubapp.data.source.repository.RepositoryDataSource
import com.akovar.githubapp.data.source.repository.RepositorySort
import timber.log.Timber

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryListPresenter(
    private val repositoryDataSource: RepositoryDataSource
) : BaseSearchListPresenter<Repository, RepositoryListContract.View>(),
    RepositoryListContract.Presenter {

    /**
     * Current selected sort holder.
     */
    private var sort: RepositorySort = Constant.UI.DEFAULT_REPOSITORY_SORT

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

    override fun onSortSubmit(sort: RepositorySort) {
        Timber.d("onSortSubmit $sort")
        this.sort = sort
        load()
    }

    override suspend fun provideQueryItemsResult(): Result<List<Repository>> {
        return repositoryDataSource.query(
            query,
            sort
        )
    }

    /**
     * By default, this presenter will research latest
     * repositories about provided topic.
     */
    override suspend fun provideItemsResult(): Result<List<Repository>> {
        return repositoryDataSource.query(
            Constant.UI.DEFAULT_QUERY,
            sort
        )
    }
}