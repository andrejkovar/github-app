package com.akovar.githubapp.di

import com.akovar.githubapp.components.repository.RepositoryContract
import com.akovar.githubapp.components.repository.RepositoryPairId
import com.akovar.githubapp.components.repository.RepositoryPresenter
import com.akovar.githubapp.components.repositorylist.RepositoryListContract
import com.akovar.githubapp.components.repositorylist.RepositoryListPresenter
import com.akovar.githubapp.components.user.UserContract
import com.akovar.githubapp.components.user.UserPresenter
import com.akovar.githubapp.data.model.Repository
import com.akovar.githubapp.data.model.User
import com.akovar.githubapp.data.source.repository.RepositoryDataSource
import com.akovar.githubapp.data.source.user.UserDataSource
import org.koin.core.qualifier.qualifier
import org.koin.java.KoinJavaComponent.get

/**
 * Created by akovar on 12/06/2020.
 */
class PresenterInjector {

    companion object {

        fun provideRepositoryListPresenter(): RepositoryListContract.Presenter {
            return RepositoryListPresenter(
                get(
                    RepositoryDataSource::class.java,
                    qualifier("repositoryDataSource")
                )
            )
        }

        fun provideRepositoryPresenter(
            repositoryPairId: RepositoryPairId
        ): RepositoryContract.Presenter<Repository, RepositoryContract.View<Repository>> {
            return RepositoryPresenter(
                repositoryPairId,
                get(
                    RepositoryDataSource::class.java,
                    qualifier("repositoryDataSource")
                )
            )
        }

        fun provideUserPresenter(userId: String): UserContract.Presenter<User> {
            return UserPresenter(
                userId,
                get(
                    UserDataSource::class.java,
                    qualifier("userDataSource")
                )
            )
        }
    }
}