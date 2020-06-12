package com.ag04.githubapp.di

import com.ag04.githubapp.components.repository.RepositoryContract
import com.ag04.githubapp.components.repository.RepositoryPresenter
import com.ag04.githubapp.components.repositorylist.RepositoryListContract
import com.ag04.githubapp.components.repositorylist.RepositoryListPresenter
import com.ag04.githubapp.components.user.UserContract
import com.ag04.githubapp.components.user.UserPresenter

/**
 * Created by akovar on 12/06/2020.
 */
class PresenterInjector {

    companion object {

        fun provideRepositoryListPresenter(): RepositoryListContract.Presenter {
            return RepositoryListPresenter(DataSourceInjector.provideRepositoryDataSource())
        }

        fun provideRepositoryPresenter(
            userLogin: String,
            repoName: String
        ): RepositoryContract.Presenter {
            return RepositoryPresenter(
                userLogin,
                repoName,
                DataSourceInjector.provideRepositoryDataSource()
            )
        }

        fun provideUserPresenter(): UserContract.Presenter {
            return UserPresenter()
        }
    }
}