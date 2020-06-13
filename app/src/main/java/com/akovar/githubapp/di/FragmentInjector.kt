package com.akovar.githubapp.di

import com.akovar.githubapp.components.repository.RepositoryFragment
import com.akovar.githubapp.components.repositorylist.RepositoryListFragment
import com.akovar.githubapp.components.user.UserFragment

/**
 * Created by akovar on 12/06/2020.
 */
class FragmentInjector {

    companion object {

        fun provideRepositoryListFragment(): RepositoryListFragment {
            return RepositoryListFragment()
        }

        fun provideRepositoryFragment(): RepositoryFragment {
            return RepositoryFragment()
        }

        fun provideUserFragment(): UserFragment {
            return UserFragment()
        }
    }
}