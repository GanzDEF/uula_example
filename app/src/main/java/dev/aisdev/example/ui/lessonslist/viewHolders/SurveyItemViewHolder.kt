package dev.aisdev.example.ui.lessonslist.viewHolders

import android.view.View
import dev.aisdev.example.entities.lesson.LessonSurvey
import dev.aisdev.example.ui.base.adapters.BaseMultiTypeAdapter
import dev.aisdev.example.ui.lessonslist.LessonsAdapter

class SurveyItemViewHolder(
    itemView: View,
    listener: LessonsAdapter.LessonsListener
) : BaseMultiTypeAdapter.ViewHolder<Any>(itemView){

    override fun bindView(item: Any) = (item as LessonSurvey).let {

    }

}
