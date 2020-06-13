package com.ag04.githubapp.components.base.details

import com.ag04.githubapp.components.base.BaseFragment

/**
 * Created by akovar on 13/06/2020.
 */
abstract class BaseDetailsFragment<T, V : BaseDetailsContract.View<T>, P : BaseDetailsContract.Presenter<T, V>>
    : BaseFragment<V, P>(),
    BaseDetailsContract.View<T>