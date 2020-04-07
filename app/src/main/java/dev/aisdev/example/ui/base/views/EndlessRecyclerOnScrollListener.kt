package dev.aisdev.example.ui.base.views

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@Suppress("MemberVisibilityCanBePrivate")
abstract class EndlessRecyclerOnScrollListener(
    private val mLinearLayoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {
    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.
    private var visibleThreshold = 5 // The minimum amount of items to have below your current scroll position before loading more.
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()
        when {
            loading && (totalItemCount > previousTotal) -> {
                loading = false
                previousTotal = totalItemCount
            }
            !loading && (totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) -> {
                currentPage++
                onLoadMore(currentPage)
                loading = true
            }
        }
    }

    fun refresh() {
        previousTotal = 0
        loading = true
        visibleThreshold = 5
        firstVisibleItem = 0
        visibleItemCount = 0
        totalItemCount = 0
        currentPage = 0
    }

    abstract fun onLoadMore(current_page: Int)
}
