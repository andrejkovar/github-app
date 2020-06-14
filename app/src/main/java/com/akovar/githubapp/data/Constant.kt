package com.akovar.githubapp.data

import com.akovar.githubapp.data.source.repository.RepositorySort

/**
 * This class holds main application constants constants.
 *
 * Created by akovar on 14/06/2020.
 */
class Constant {

    class UI {

        companion object {

            /**
             * By default, repository list presenter will
             * research latest repositories about default topic.
             */
            const val DEFAULT_QUERY: String = "android"

            /**
             * Default repository sort if not set.
             */
            val DEFAULT_REPOSITORY_SORT = RepositorySort(
                stars = true,
                forks = false,
                lastUpdated = false
            )
        }
    }

    class DI {

        companion object {

            const val NAMED_REMOTE_REPOSITORY_DATA_SOURCE = "remoteRepositoryDataSource"
            const val NAMED_LOCAL_REPOSITORY_DATA_SOURCE = "localRepositoryDataSource"
            const val NAMED_REPOSITORY_DATA_SOURCE = "repositoryDataSource"

            const val NAMED_REMOTE_USER_DATA_SOURCE = "remoteUserDataSource"
            const val NAMED_LOCAL_USER_DATA_SOURCE = "localUserDataSource"
            const val NAMED_USER_DATA_SOURCE = "userDataSource"
        }
    }
}