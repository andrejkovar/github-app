package com.ag04.githubapp.di

import com.ag04.githubapp.components.repository.RepositoryContract
import com.ag04.githubapp.components.repository.RepositoryPairId
import com.ag04.githubapp.components.repository.RepositoryPresenter
import com.ag04.githubapp.components.repositorylist.RepositoryListContract
import com.ag04.githubapp.components.repositorylist.RepositoryListPresenter
import com.ag04.githubapp.components.user.UserContract
import com.ag04.githubapp.components.user.UserPresenter
import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.model.User

/**
 * Created by akovar on 12/06/2020.
 */
class PresenterInjector {

    companion object {

        fun provideRepositoryListPresenter(): RepositoryListContract.Presenter {
            return RepositoryListPresenter(DataSourceInjector.provideRepositoryDataSource())
        }

        fun provideRepositoryPresenter(
            repositoryPairId: RepositoryPairId
        ): RepositoryContract.Presenter<Repository, RepositoryContract.View<Repository>> {
            return RepositoryPresenter(
                repositoryPairId,
                DataSourceInjector.provideRepositoryDataSource()
            )
        }

        fun provideUserPresenter(userId: String): UserContract.Presenter<User> {
            return UserPresenter(userId, DataSourceInjector.provideUserDataSource())
        }
    }
}