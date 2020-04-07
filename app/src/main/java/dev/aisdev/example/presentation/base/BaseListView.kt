package dev.aisdev.example.presentation.base

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BaseListView <I>: BaseProgressErrorView {
    fun updateList(list: List<I>)
    fun refreshPagination()

    fun showEmptyList()
    fun hideEmptyList()

    fun hideContent()
    fun showContent()

    fun disablePagination()
    fun enablePagination()
}