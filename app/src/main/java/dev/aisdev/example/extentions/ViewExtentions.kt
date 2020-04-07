@file:Suppress("unused")

package dev.aisdev.example.extentions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

fun View.visible(bool: Boolean)  =
    if (bool) View.VISIBLE else View.GONE

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun Context.toast(message: String): Toast = Toast
    .makeText(this,
        message,
        Toast.LENGTH_SHORT)
    .apply { show() }

fun TextView.setHtmlText(htmlText: String) {
    text = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
        else -> @Suppress("DEPRECATION") Html.fromHtml(htmlText)
    }
}

fun TextView.addOnTextChanged(onChange: (text: CharSequence?) -> Unit) =
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) { /**ignored*/ }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { /**ignored*/ }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (!p0.isNullOrEmpty()) { onChange.invoke(p0) }
        }
    })


fun EditText.hideSoftKeyboardOnReturnKeyPressed() {
    imeOptions = EditorInfo.IME_ACTION_DONE
    setOnKeyListener { _, keyCode, event ->
        if (keyCode == KeyEvent.KEYCODE_ENTER && (event.action == KeyEvent.ACTION_DOWN)) {
            hideKeyboard(this)
            return@setOnKeyListener true
        }
        return@setOnKeyListener false
    }
}

fun View.rotateViewToQuarterCircleClockWise() {
    val animator = ObjectAnimator.ofFloat(this, View.ROTATION, -360f, -270f)
    animator.run {
        duration = 500
        repeatCount = 0
        disableViewDuringAnimation(this@rotateViewToQuarterCircleClockWise)
        start()
    }
}

fun View.rotateViewToQuarterCircleContraClockWise() {
    val animator = ObjectAnimator.ofFloat(this, View.ROTATION,  -270f, -360f)
    animator.run {
        duration = 500
        repeatCount = 0
        disableViewDuringAnimation(this@rotateViewToQuarterCircleContraClockWise)
        start()
    }
}

fun ObjectAnimator.disableViewDuringAnimation(view: View) = this
    .addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) { view.isEnabled = false }
        override fun onAnimationEnd(animation: Animator?) { view.isEnabled = true; removeAllListeners() }
    })

fun View.setVisible(animate: Boolean = true) = when {
    animate -> animate().alpha(1f).setDuration(500).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator) {
            super.onAnimationStart(animation)
            visibility = View.VISIBLE
        }
    }).start()
    else -> visibility = View.VISIBLE
}

fun View.invisible(animate: Boolean = true) = hide(View.INVISIBLE, animate)

fun View.gone(animate: Boolean = true) = hide(View.GONE, animate)

private fun View.hide(hidingStrategy: Int, animate: Boolean = true) = when {
    animate -> animate().alpha(0f).setDuration(500).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            visibility = hidingStrategy
        }
    }).start()
    else -> visibility = hidingStrategy
}

fun hideKeyboard(view: View) {
    val context = view.context
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}


