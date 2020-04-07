@file:Suppress("unused", "MemberVisibilityCanBePrivate")

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

    override fun onCreateViewHolder(parent: ViewGroup, layoutRes: Int) = providers
        .getValue(layoutRes)
        .provideViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(layoutRes, parent, false))

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = typeSelector(items[position])

    override fun onBindViewHolder(holder: ViewHolder<Item>, position: Int) {
        getSafelyItem(position)?.run { holder.bindView(this) }
    }

    fun getSafelyItem(position: Int) = when {
        items.size > position -> getItem(position)
        else -> null
    }

    abstract class ViewHolder<Item>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val context: Context get() = itemView.context

        abstract fun bindView(item: Item)
    }
}