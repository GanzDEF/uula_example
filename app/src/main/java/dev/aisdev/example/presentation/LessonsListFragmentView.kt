package dev.aisdev.example.presentation

import dev.aisdev.example.presentation.base.BaseListView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface LessonsListFragmentView : BaseListView<Any> {

}
