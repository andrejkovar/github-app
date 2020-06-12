package com.ag04.githubapp.components.injector

import com.ag04.githubapp.components.repository.RepositoryFragment
import com.ag04.githubapp.components.repositorylist.RepositoryListFragment
import com.ag04.githubapp.components.user.UserFragment

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