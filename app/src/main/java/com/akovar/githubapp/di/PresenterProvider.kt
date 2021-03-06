package com.akovar.githubapp.di

import com.akovar.githubapp.components.landing.LandingContract
import com.akovar.githubapp.components.landing.LandingPresenter
import com.akovar.githubapp.components.login.LoginContract
import com.akovar.githubapp.components.login.LoginPresenter
import com.akovar.githubapp.components.repository.RepositoryContract
import com.akovar.githubapp.components.repository.RepositoryPairId
import com.akovar.githubapp.components.repository.RepositoryPresenter
import com.akovar.githubapp.components.repositorylist.RepositoryListContract
import com.akovar.githubapp.components.repositorylist.RepositoryListPresenter
import com.akovar.githubapp.components.user.UserContract
import com.akovar.githubapp.components.user.UserPresenter
import com.akovar.githubapp.data.model.Repository
import com.akovar.githubapp.data.model.User

/**
 * Created by akovar on 12/06/2020.
 */
class PresenterProvider {

    companion object {

        fun provideLoginPresenter(): LoginContract.Presenter {
            return LoginPresenter(ApplicationProvider.provideAuthClient())
        }

        fun provideLandingPresenter(): LandingContract.Presenter {
            return LandingPresenter(ApplicationProvider.provideAuthClient())
        }

        fun provideRepositoryListPresenter(): RepositoryListContract.Presenter {
            return RepositoryListPresenter(
                DataSourceProvider.provideRepositoryDataSource(),
                DataSourceProvider.provideUserDataSource(),
                ApplicationProvider.provideAuthClient()
            )
        }

        fun provideRepositoryPresenter(
            repositoryPairId: RepositoryPairId
        ): RepositoryContract.Presenter<Repository, RepositoryContract.View<Repository>> {
            return RepositoryPresenter(
                repositoryPairId,
                DataSourceProvider.provideRepositoryDataSource()
            )
        }

        fun provideUserPresenter(userId: String): UserContract.Presenter<User> {
            return UserPresenter(userId, DataSourceProvider.provideUserDataSource())
        }
    }
}