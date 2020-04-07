package dev.aisdev.example.ui.base.adapters


import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.aisdev.example.R

@Suppress("unused")
abstract class BasePaginationAdapter<Item>(
    list: List<Item> = listOf(),
    providers: Map<Int, ViewHolderProvider<Item>>,
    typeSelector: (Item) -> Int,
    diffFactory: DiffCallbackFactory<Item> = { source, target -> SimpleDiffCallback(source, target) }
) : BaseMultiTypeAdapter<Item>(list.toMutableList(), providers, typeSelector, diffFactory) {

    private var withPagination = true
    @LayoutRes
    open var progressLayoutRes = R.layout.progress_item

    override fun onCreateViewHolder(parent: ViewGroup, layoutRes: Int): ViewHolder<Item> {
        return if (layoutRes == progressLayoutRes) {
            ProgressViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false))
        } else {
            super.onCreateViewHolder(parent, layoutRes)
        }
    }

    override fun getItemViewType(position: Int) =
        if (super.getItemCount() in 1..position) {
            progressLayoutRes
        } else {
            super.getItemViewType(position)
        }


    override fun getItemCount(): Int {
        var originalCount = super.getItemCount()
        if (withPagination && super.getItemCount() > 0) {
            originalCount++
        }
        return originalCount
    }

    class ProgressViewHolder<Item>(itemView: View): ViewHolder<Item>(itemView) {
        override fun bindView(item: Item) { /*ignore*/ }
    }

    fun togglePagination(withPagination: Boolean) {
        if (this@BasePaginationAdapter.withPagination == !withPagination) {
            this@BasePaginationAdapter.withPagination = withPagination
            if (this@BasePaginationAdapter.withPagination) {
                notifyItemInserted(itemCount - 1)
            } else {
                notifyItemRemoved(itemCount - 1)
            }
        }
    }

    fun setPagination(withPagination: Boolean) {
        if (this@BasePaginationAdapter.withPagination != withPagination) {
            if (withPagination) {
                onPagination()
            } else {
                offPagination()
            }
        }
    }

    private fun offPagination() {
        if (withPagination) {
            withPagination = false
            notifyItemRemoved(itemCount)
        }
    }

    private fun onPagination() {
        if (!withPagination) {
            withPagination = true
            notifyItemInserted(itemCount - 1)
        }
    }

    override fun update(newList: List<Item>) {
        val tmpPagination = withPagination
        offPagination()
        super.update(newList)
        if (tmpPagination) { onPagination() }
    }

    fun getPagination() = withPagination
}