package dev.aisdev.example.extentions

import android.graphics.Rect
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

fun View.updatePadding(
    left: Int = paddingLeft,
    top: Int = paddingTop,
    right: Int = paddingRight,
    bottom: Int = paddingBottom
) = setPadding(left, top, right, bottom)

fun View.addSystemTopPadding(
    targetView: View = this,
    isConsumed: Boolean = false
) = doOnApplyWindowInsets { _, insets, initialPadding ->
        targetView.updatePadding(top = initialPadding.top + insets.systemWindowInsetTop)
        when {
            isConsumed -> insets.replaceSystemWindowInsets(
                Rect(
                    insets.systemWindowInsetLeft,
                    0,
                    insets.systemWindowInsetRight,
                    insets.systemWindowInsetBottom
                )
            )
            else -> insets
        }
    }

fun View.addSystemBottomPadding(
    targetView: View = this,
    isConsumed: Boolean = false
) = doOnApplyWindowInsets { _, insets, initialPadding ->
        targetView.updatePadding(bottom =  insets.systemWindowInsetBottom + initialPadding.bottom)
        when {
            isConsumed -> insets.replaceSystemWindowInsets(Rect(
                    insets.systemWindowInsetLeft,
                    insets.systemWindowInsetTop,
                    insets.systemWindowInsetRight,
                    0
                ))
            else -> insets
        }
    }

fun View.doOnApplyWindowInsets(block: (View, insets: WindowInsetsCompat, initialPadding: Rect) -> WindowInsetsCompat) {
    val initialPadding = recordInitialPaddingForView(this)
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        block(v, insets, initialPadding)
    }
    requestApplyInsetsWhenAttached()
}

private fun recordInitialPaddingForView(view: View) =
    Rect(view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)

private fun View.requestApplyInsetsWhenAttached() = when {
    isAttachedToWindow -> ViewCompat.requestApplyInsets(this)
    else -> addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(v: View) {
            v.removeOnAttachStateChangeListener(this)
            ViewCompat.requestApplyInsets(v)
        }
        override fun onViewDetachedFromWindow(v: View) = Unit
    })
}
