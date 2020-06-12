package com.ag04.githubapp.injector

import com.ag04.githubapp.components.repository.RepositoryContract
import com.ag04.githubapp.components.repository.RepositoryPresenter
import com.ag04.githubapp.components.repositorylist.RepositoryListContract
import com.ag04.githubapp.components.repositorylist.RepositoryListPresenter
import com.ag04.githubapp.components.user.UserContract
import com.ag04.githubapp.components.user.UserPresenter
import com.ag04.githubapp.data.model.Repository

/**
 * Created by akovar on 12/06/2020.
 */
class PresenterInjector {

    companion object {

        fun provideRepositoryListPresenter(): RepositoryListContract.Presenter<Repository> {
            return RepositoryListPresenter(DataSourceInjector.provideRepositoryDataSource())
        }

        fun provideRepositoryPresenter(): RepositoryContract.Presenter {
            return RepositoryPresenter()
        }

        fun provideUserPresenter(): UserContract.Presenter {
            return UserPresenter()
        }
    }
}