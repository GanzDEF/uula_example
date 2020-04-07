package dev.aisdev.example.ui.base

import androidx.recyclerview.widget.RecyclerView

@Suppress("unused")
abstract class BaseListAdapter<T>(private val enableLoader: Boolean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected val items: MutableList<T?> = mutableListOf()

    override fun getItemCount(): Int = items.size

    fun addItem(item: T) {
        items.add(item)
        notifyItemInserted(items.size-1)
    }

    open fun addList(list: List<T>) {
        if (items.isNotEmpty() && items.last() == null) {
            val loaderIndex = items.size - 1
            items.removeAt(loaderIndex)
            notifyItemRemoved(loaderIndex)
        }

        val start = items.size
        items.addAll(list)

        var end = items.size
        if (enableLoader) {
            items.add(null)
            ++end
        }

        notifyItemRangeInserted(start, end)
    }

    fun updateList(list: List<T>) {
        items.clear()
        items.addAll(list)

        if (enableLoader) {
            items.add(null)
        }

        notifyDataSetChanged()
    }

    fun changeItem(item: T, index: Int) {
        items[index] = item
        notifyItemChanged(index)
    }

    fun removeItem(index: Int) {
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    fun removeLoader() {
        if (items.isNotEmpty() && items.last() == null) {
            val index = items.size - 1
            items.removeAt(items.size - 1)
            notifyItemRemoved(index)
        }
    }
}