package com.akovar.githubapp.di

import com.akovar.githubapp.components.repository.RepositoryFragment
import com.akovar.githubapp.components.repositorylist.RepositoryListFragment
import com.akovar.githubapp.components.user.UserFragment
import org.koin.dsl.module

/**
 * Created by akovar on 12/06/2020.
 */
val fragmentModule = module {

    factory {
        RepositoryListFragment()
    }

    factory {
        RepositoryFragment()
    }

    factory {
        UserFragment()
    }
}