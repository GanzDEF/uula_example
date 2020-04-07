package dev.aisdev.example.presentation.base

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BaseView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(msg: String)
    fun refreshScreen() { /*it is not required method*/ }

    fun exit() { /*it is not required method*/ }
    fun copyTextToBuffer(text: String) {/*it is not required method*/}


}