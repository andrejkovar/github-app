package com.akovar.githubapp.components.base.details

import com.akovar.githubapp.components.base.BaseFragment

/**
 * This base details fragment doesn't have any functionality for now.
 *
 * Created by akovar on 13/06/2020.
 */
abstract class BaseDetailsFragment<
        T,
        V : BaseDetailsContract.View<T>,
        P : BaseDetailsContract.Presenter<T, V>>
    : BaseFragment<V, P>(),
    BaseDetailsContract.View<T>