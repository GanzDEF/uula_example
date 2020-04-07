package dev.aisdev.example.presentation.base

@Suppress("unused")
abstract class BaseListPresenter<I: Any, V: BaseListView<I>> : BasePresenter<V>() {

    var needClearBeforeApplyList: Boolean = false
    val list = mutableListOf<I>()

    override fun onFirstViewAttach() = refresh()

    open fun refresh() {
        needClearBeforeApplyList = true
        list.clear()
        onLoadMore(0)
    }

    fun onLoadMore(page: Int) {
        if (page == 0) { needClearBeforeApplyList = true }
        loadList(page)
    }

    fun clear() = list.clear()

    open fun applyList(newList: List<I>, hasNextPage: Boolean = false) {
        val needRefreshPagination = list.isEmpty()
        if (needClearBeforeApplyList) { list.clear() }
        list.addAll(newList)
        updateViewList()
        togglePagination(hasNextPage)

        if (needRefreshPagination) {
            refreshPagination()
        }
    }

    private fun togglePagination(hasNextPage: Boolean) = when {
            !hasNextPage -> viewState.disablePagination()
            else -> viewState.enablePagination()
    }

    private fun refreshPagination() = viewState.refreshPagination()

    private fun updateViewList() {
        toggleEmptyScreen(list.isEmpty())
        viewState.updateList(list)
    }

    private fun toggleEmptyScreen(listIsEmpty: Boolean) = when {
        listIsEmpty -> {
            viewState.showEmptyList()
            viewState.hideContent()
        }
        else -> {
            viewState.hideEmptyList()
            viewState.showContent()
        }
    }

    abstract fun loadList(page: Int)
}