package dev.aisdev.example.ui.lessonslist.viewHolders

import android.view.View
import androidx.core.content.ContextCompat
import dev.aisdev.example.R
import dev.aisdev.example.entities.lesson.LessonHeader
import dev.aisdev.example.ui.base.adapters.BaseMultiTypeAdapter
import kotlinx.android.synthetic.main.view_lesson_header_detail.view.*

class HeaderItemViewHolder(
    itemView: View
) : BaseMultiTypeAdapter.ViewHolder<Any>(itemView){

    override fun bindView(item: Any) = (item as LessonHeader).let{
        itemView.tvLessonsProgressCount.text = item.title
        itemView.tvLessonProgressTitle.text = itemView.context.getString(R.string.lessons_progress)
    }

}
