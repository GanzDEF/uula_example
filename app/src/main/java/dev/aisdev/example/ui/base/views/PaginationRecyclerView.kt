package dev.aisdev.example.ui.base.views

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.aisdev.example.Contract
import dev.aisdev.example.R

@Suppress("unused", "MemberVisibilityCanBePrivate")
class PaginationRecyclerView : BaseRecyclerView {

    private var orientation: Int = 0
    var endlessRecyclerOnScrollListener: EndlessRecyclerOnScrollListener? = null
    var listener: OnLoadMore? = null
    var withPagination = false
    val scrollListeners: MutableList<RecyclerView.OnScrollListener> = mutableListOf()

    constructor(context: Context): super(context) {
        init(null)
    }
    constructor(context: Context, @Nullable attrs: AttributeSet): super(context, attrs) {
        init(attrs)
    }
    constructor(context: Context, @Nullable attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    fun init(attrs: AttributeSet?, defStyle: Int = 0) {
        if (attrs != null) { instanceAttrs(attrs, defStyle) }
        else { setDefParams() }
        initLayoutManager()
        instanceScrollListener()
    }

    private fun initLayoutManager() {
        layoutManager = LinearLayoutManager(
            context,
            if (orientation == Contract.PaginationRecyclerView.ORIENTATION.VERTICAL.value) {
                LinearLayoutManager.VERTICAL
            } else {
                LinearLayoutManager.HORIZONTAL
            },
            false
        )
    }

    private fun setDefParams() {
        orientation = Contract.PaginationRecyclerView.ORIENTATION.VERTICAL.value
    }

    private fun instanceAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.PaginationRecyclerView, 0, defStyle)
        if (typedArray.hasValue(R.styleable.PaginationRecyclerView_orientation)) {
            orientation = typedArray.getInt(R.styleable.PaginationRecyclerView_orientation, Contract.PaginationRecyclerView.ORIENTATION.VERTICAL.value)
        } else {
            orientation = Contract.PaginationRecyclerView.ORIENTATION.VERTICAL.value
        }
    }

    private fun instanceScrollListener() {
        if (layoutManager is LinearLayoutManager) {
            if (endlessRecyclerOnScrollListener != null) {
                removeOnScrollListener(endlessRecyclerOnScrollListener!!)
            }
            endlessRecyclerOnScrollListener = object : EndlessRecyclerOnScrollListener(layoutManager as LinearLayoutManager) {
                override fun onLoadMore(currentPage: Int) {
                    if (withPagination) {
                        listener?.onLoadMore(currentPage)
                    }
                }
            }
            addOnScrollListener(endlessRecyclerOnScrollListener!!)
        }
    }

    override fun addOnScrollListener(listener: OnScrollListener) {
        scrollListeners.add(listener)
        super.addOnScrollListener(listener)
    }

    fun refreshPagination() {
        endlessRecyclerOnScrollListener?.refresh()
    }

    interface OnLoadMore {
        fun onLoadMore(pageNumber: Int)
    }

    fun setOrientation(orientation: Int) {
        this@PaginationRecyclerView.orientation = orientation
        initLayoutManager()
    }
}