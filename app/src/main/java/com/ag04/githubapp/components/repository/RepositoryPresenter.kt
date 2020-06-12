package com.ag04.githubapp.components.repository

import com.ag04.githubapp.components.base.BasePresenter
import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.source.Result
import com.ag04.githubapp.data.source.repository.RepositoryDataSource
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryPresenter(
    private val userLogin: String,
    private val repoName: String,
    private val repositoryDataSource: RepositoryDataSource
) : BasePresenter<RepositoryContract.View>(),
    RepositoryContract.Presenter {

    /**
     * Scope responsible to load repository data.
     */
    private val scope = MainScope()

    /**
     * Repository data holder.
     */
    private lateinit var repository: Repository

    override fun onViewReady() {
        super.onViewReady()
        loadRepositoryDetails(userLogin, repoName)
    }

    override fun onRefresh() {
        loadRepositoryDetails(userLogin, repoName)
    }

    override fun onRepositoryOwnerClick() {
        // TODO navigate to owner details
    }

    /**
     * Loads repository data and notifies view about result.
     */
    private fun loadRepositoryDetails(userLogin: String, repoName: String) {
        Timber.d("loadRepositoryDetails")

        scope.launch {
            view?.showLoadingIndicator(true)
            view?.showDetails(false)

            val result = repositoryDataSource.getUserRepository(userLogin, repoName)
            onLoadedRepositoryDetails(result)

            view?.showLoadingIndicator(false)
            view?.showDetails(true)
        }
    }

    /**
     * Invoked when repository data is loaded and notifies view.
     */
    private fun onLoadedRepositoryDetails(result: Result<Repository>) {
        Timber.d("onLoadedRepositoryDetails $result")

        if (result is Result.Success) {
            repository = result.item
            view?.onRepositoryLoaded(repository)
        } else {
            view?.onError((result as Result.Error).error.code)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}