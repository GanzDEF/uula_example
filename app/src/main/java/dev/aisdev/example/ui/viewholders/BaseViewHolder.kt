package dev.aisdev.example.ui.viewholders

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(private val view: View) : RecyclerView.ViewHolder(view),
    View.OnClickListener, View.OnLongClickListener {

    val thisContext: Context
        get() = view.context

    val thisView: View
        get() = view

    init {
        view.setOnClickListener(this)
        view.setOnClickListener(this)
    }

    @Throws(Exception::class)
    abstract fun bindData(data: Any)

}