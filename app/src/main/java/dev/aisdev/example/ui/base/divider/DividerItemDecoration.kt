package dev.aisdev.example.ui.base.divider

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.aisdev.example.R
import kotlin.math.roundToInt

@Suppress("unused", "MemberVisibilityCanBePrivate")
open class DividerItemDecoration(
    context: Context,
    private val padding: Int = 0,
    private val drawableRes: Drawable = ContextCompat.getDrawable(context, R.drawable.divider_base)!!,
    private val needDrawFunction: (RecyclerView.Adapter<*>, Int) -> Boolean = { _, _ -> true }) : RecyclerView.ItemDecoration() {

    private val bounds = Rect()
    protected val divider = drawableRes

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.adapter != null && needDrawFunction(parent.adapter!!, parent.getChildAdapterPosition(view))) {
            outRect.set(0, 0, 0, divider.intrinsicHeight)
        } else {
            outRect.set(0, 0, 0, 0)
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null) {
            return
        }
        draw(c, parent)
    }

    protected open fun getDividerBottom(child: View) = bounds.bottom + child.translationY.roundToInt()

    private fun draw(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val left: Int
        val right: Int

        when {
            parent.clipToPadding -> {
                left = parent.paddingLeft + padding
                right = parent.width - parent.paddingRight - padding
                canvas.clipRect(left, parent.paddingTop, right, parent.height - parent.paddingBottom)
            }
            else -> {
                left = padding
                right = parent.width - padding
            }
        }
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val adapterPosition = parent.getChildAdapterPosition(child)
            if (parent.adapter != null && needDrawFunction(parent.adapter!!, adapterPosition)) {
                parent.getDecoratedBoundsWithMargins(child, bounds)
                val bottom = getDividerBottom(child)
                val top = bottom - divider.intrinsicHeight
                divider.setBounds(left, top, right, bottom)
                divider.draw(canvas)
            }
        }
        canvas.restore()
    }
}