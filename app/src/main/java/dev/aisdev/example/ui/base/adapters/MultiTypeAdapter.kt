package dev.aisdev.example.ui.base.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

interface ViewHolderProvider<Item> {
    fun provideViewHolder(itemView: View): BaseMultiTypeAdapter.ViewHolder<Item>
}

fun <Item> viewHolderProvider(provide: (View) -> BaseMultiTypeAdapter.ViewHolder<Item>) = object: ViewHolderProvider<Item> {
    override fun provideViewHolder(itemView: View) = provide(itemView)
}

open class BaseMultiTypeAdapter<Item>(
    items: MutableList<Item>,
    val providers: Map<Int, ViewHolderProvider<Item>>,
    val typeSelector: (Item) -> Int,
    diffFactory: DiffCallbackFactory<Item> = { source, target -> SimpleDiffCallback(source, target) }
) : UpdatableAdapter<Item, BaseMultiTypeAdapter.ViewHolder<Item>>(items, diffFactory) {

    override fun onCreateViewHolder(parent: ViewGroup, layoutRes: Int): ViewHolder<Item> {
        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return providers.getValue(layoutRes).provideViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = typeSelector(items[position])

    override fun onBindViewHolder(holder: ViewHolder<Item>, position: Int) {
        val item = getSafelyItem(position)
        if (item != null) {
            holder.bindView(item)
        }
    }

    fun getSafelyItem(position: Int) = if (items.size > position) { getItem(position) } else { null }

    abstract class ViewHolder<Item>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val context: Context get() = itemView.context

        abstract fun bindView(item: Item)
    }
}