package dev.aisdev.example.ui.base.views

import android.content.Context
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet

open class BaseRecyclerView : RecyclerView {

    var upDownScrollListener: UpDownOnScrollListener? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, @Nullable attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)
        if (dy > 0) {
            upDownScrollListener?.onScrollUp()
        } else if (dy < 0) {
            upDownScrollListener?.onScrollDown()
        }
    }

    interface UpDownOnScrollListener {
        fun onScrollUp()
        fun onScrollDown()
    }

}