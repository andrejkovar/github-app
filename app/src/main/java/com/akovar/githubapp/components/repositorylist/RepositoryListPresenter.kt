package com.akovar.githubapp.components.repositorylist

import com.akovar.githubapp.client.AuthClient
import com.akovar.githubapp.client.github.GitHubCredentials
import com.akovar.githubapp.client.github.GitHubToken
import com.akovar.githubapp.components.base.searchlist.BaseSearchListPresenter
import com.akovar.githubapp.data.Constant
import com.akovar.githubapp.data.model.Repository
import com.akovar.githubapp.data.model.User
import com.akovar.githubapp.data.source.DataSource
import com.akovar.githubapp.data.source.Result
import com.akovar.githubapp.data.source.repository.Query
import com.akovar.githubapp.data.source.repository.RepositoryId
import com.akovar.githubapp.data.source.repository.RepositorySort
import com.akovar.githubapp.data.source.user.UserId
import com.akovar.githubapp.data.source.user.UserMe
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryListPresenter(
    private val repositoryDataSource: DataSource<Repository, RepositoryId>,
    private val userDataSource: DataSource<User, UserId>,
    private val authClient: AuthClient<GitHubToken, GitHubCredentials>
) : BaseSearchListPresenter<Repository, RepositoryListContract.View>(),
    RepositoryListContract.Presenter {

    /**
     * Current selected sort holder.
     */
    private var sort: RepositorySort = Constant.UI.DEFAULT_REPOSITORY_SORT

    /**
     * User coroutine scope holder.
     */
    private val userScope = MainScope()

    override fun onViewReady() {
        super.onViewReady()
        view?.showUserProfile(authClient.hasToken())
    }

    override fun onItemClick(item: Repository) {
        super.onItemClick(item)
        view?.navigateToRepositoryDetails(item)
    }

    override fun onOwnerAvatarClick(item: Repository) {
        Timber.d("onOwnerAvatarClick: ${item.id}")
        view?.navigateToUserDetails(item.user)
    }

    override fun onMyProfileClick() {
        Timber.d("onMyProfileClick")

        userScope.launch {
            view?.showLoadingProgress(true)

            val result = userDataSource.getById(UserMe)
            if (result is Result.Success) {
                view?.navigateToMyProfileDetails(result.item)
            } else {
                view?.onError(0)
            }

            view?.showLoadingProgress(false)
        }
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
        return repositoryDataSource.getAll(Query(query, sort))
    }

    /**
     * By default, this presenter will research latest
     * repositories about provided topic.
     */
    override suspend fun provideItemsResult(): Result<List<Repository>> {
        return repositoryDataSource.getAll(
            Query(
                Constant.UI.DEFAULT_QUERY,
                sort
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        userScope.cancel()
    }
}