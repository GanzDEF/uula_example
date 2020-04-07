package dev.aisdev.example.ui.base

import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import dev.aisdev.example.R
import dev.aisdev.example.extentions.visible
import dev.aisdev.example.presentation.base.BaseListPresenter
import dev.aisdev.example.presentation.base.BaseListView
import dev.aisdev.example.ui.base.adapters.BasePaginationAdapter
import dev.aisdev.example.ui.base.views.PaginationRecyclerView
import kotlinx.android.synthetic.main.layout_base_list.*


abstract class BaseListFragment<
        ITEM: Any,
        VIEW: BaseListView<ITEM>,
        PRESENTER: BaseListPresenter<ITEM, VIEW>,
        A: BasePaginationAdapter<ITEM>
        > : BaseProgressErrorFragment(),
            BaseListView<ITEM>,
            PaginationRecyclerView.OnLoadMore {

    open var withPagination: Boolean = false
    open var withRefresher: Boolean = false

    val listPresenter: PRESENTER by lazy { initPresenter() }

    abstract fun initPresenter(): PRESENTER

    val adapter: A by lazy { initAdapter() }

    open val emptyListLayoutRes = R.layout.layout_base_empty_list
    private var emptyLayout: View? = null
    override val layoutRes = R.layout.layout_base_list

    val rlRefresher: SwipeRefreshLayout? by lazy { getRefresherLayout() }
    val rvList: PaginationRecyclerView by lazy { getRecyclerView() }

    abstract fun initAdapter(): A

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val layout = super.onCreateView(inflater, container, savedInstanceState)
        layout.findViewById<FrameLayout>(R.id.flListContent)?.run {
            onCreateEmptyList(inflater, container?.findViewById(R.id.flListContent))?.let {
                emptyLayout = it
                this@run.addView(emptyLayout)
                hideEmptyList()
            }
        }
        return layout
    }

    open fun onCreateEmptyList(inflater: LayoutInflater, container: ViewGroup?): View? {
        return if (emptyListLayoutRes != -1) { inflater.inflate(emptyListLayoutRes, container, false) }
        else { null }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView(rvList)
        initRefresher()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRefresher() {
        rlRefresher?.run {
            isEnabled = withRefresher
            isRefreshing = withRefresher
            setOnRefreshListener { listPresenter.refresh() }
        }
    }

    open fun initRecyclerView(recyclerView: PaginationRecyclerView) {
        recyclerView.adapter = adapter
        recyclerView.withPagination = this@BaseListFragment.withPagination
        recyclerView.listener = this
    }

    override fun onLoadMore(pageNumber: Int) {
        listPresenter.onLoadMore(pageNumber)
    }

    override fun updateList(list: List<ITEM>) {
        rlRefresher?.isRefreshing = false
        adapter.update(list)
    }

    override fun refreshPagination() {
        rvList.refreshPagination()
    }

    override fun showEmptyList() {
        emptyLayout?.visible(true)
    }

    override fun hideEmptyList() {
        emptyLayout?.visible(false)
    }

    override fun showContent() {
        rvList.visible(true)
    }

    override fun hideContent() {
        rvList.visible(true)
    }

    override fun disablePagination() {
        rvList.withPagination = false
        adapter.setPagination(false)
    }

    override fun enablePagination() {
        rvList.withPagination = true
        adapter.setPagination(true)
    }

    override fun exit() {
        activity?.onBackPressed()
    }

    open fun getRefresherLayout(): SwipeRefreshLayout? = rlRefresh

    open fun getRecyclerView(): PaginationRecyclerView = prvList
}