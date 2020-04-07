package dev.aisdev.example.ui.base.adapters

import androidx.recyclerview.widget.DiffUtil

typealias DiffCallbackFactory<T> = (List<T>, List<T>) -> DiffUtil.Callback

open class SimpleDiffCallback<T>(
    val source: List<T>,
    val target: List<T>
): DiffUtil.Callback() {
    override fun getOldListSize() = source.size
    override fun getNewListSize() = target.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = source[oldItemPosition] == target[newItemPosition]
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = source[oldItemPosition]?.equals(target[newItemPosition]) ?: false
}