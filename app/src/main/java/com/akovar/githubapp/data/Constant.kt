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

    class HTTP {

        companion object {
            const val ENDPOINT_USER_ME = "/user"
            const val ENDPOINT_USERS = "/users/{login}"
            const val ENDPOINT_USER_REPO = "/repos/{ownerLogin}/{repoName}"
            const val ENDPOINT_REPOSITORY_SEARCH = "/search/repositories"
        }
    }
}