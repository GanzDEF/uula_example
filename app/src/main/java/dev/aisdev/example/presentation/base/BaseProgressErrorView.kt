package dev.aisdev.example.presentation.base

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BaseProgressErrorView : BaseView {

    fun showProgress()
    fun hideProgress()
    fun showError()
    fun hideError()
}

