package com.ag04.githubapp.data.source.base

/**
 * Created by akovar on 11/06/2020.
 */
class Sort {
}


sealed class SortOrder

object AscSortOrder : SortOrder()

object DescSortOrder : SortOrder()