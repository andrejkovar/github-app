package com.ag04.githubapp.components.repository

import com.ag04.githubapp.components.base.BaseContract

/**
 * Created by akovar on 08/06/2020.
 */
interface RepositoryContract {

    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {

    }
}