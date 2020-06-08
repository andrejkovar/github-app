package com.ag04.githubapp.components.user

import com.ag04.githubapp.components.base.BaseContract

/**
 * Created by akovar on 08/06/2020.
 */
interface UserContract {

    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {

    }
}