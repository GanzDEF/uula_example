package dev.aisdev.example.ui.lessonslist.viewHolders

import android.view.View
import dev.aisdev.example.entities.lesson.LessonFooter
import dev.aisdev.example.extentions.visible
import dev.aisdev.example.ui.base.adapters.BaseMultiTypeAdapter
import dev.aisdev.example.ui.lessonslist.LessonsAdapter
import kotlinx.android.synthetic.main.view_lesson_navigation.view.*

class FooterItemViewHolder(
    itemView: View,
    private val listener: LessonsAdapter.LessonsListener
) : BaseMultiTypeAdapter.ViewHolder<Any>(itemView){

    override fun bindView(item: Any) = (item as LessonFooter).let {
        itemView.invalidate()
        itemView.btnNext.run{
            visible(item.next)
            setOnClickListener { listener.onNextClicked() } }
        itemView.btnPrevious.run {
            visible(item.prev)
            setOnClickListener { listener.onPrevClicked() } }
    }

}
