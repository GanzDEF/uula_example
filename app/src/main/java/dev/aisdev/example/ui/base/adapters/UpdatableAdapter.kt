package dev.aisdev.example.ui.base.adapters

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

@Suppress("unused")
abstract class UpdatableAdapter<Item, VH: RecyclerView.ViewHolder>(
    val items: MutableList<Item>,
    val diffFactory: DiffCallbackFactory<Item> = { source, target -> SimpleDiffCallback(source, target) }
) : RecyclerView.Adapter<VH>() {

    override fun getItemCount() = items.size
    fun getItem(position: Int) = items[position]

    open fun update(item: Item) {
        val itemPosition = items.indexOf(item)
        if (itemPosition >= 0) {
            items[itemPosition] = item
            notifyItemChanged(itemPosition)
        }
    }

    open fun update(newList: List<Item>) {
        val callback = diffFactory(items.toMutableList(), newList)
        items.run {
            clear()
            addAll(newList)
        }
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
    }

    open fun addItem(position: Int = items.size, item: Item) {
        when {
            items.isEmpty() || position >= items.size -> items.add(item)
            else -> items.add(position, item)
        }
        notifyItemInserted(position)
    }

    open fun addItems(items: List<Item>) {
        val startPosition = this@UpdatableAdapter.items.size
        this@UpdatableAdapter.items.addAll(items)
        notifyItemRangeInserted(startPosition, items.size)
    }
}