package dev.aisdev.example.ui.lessonslist.viewHolders

import android.view.View
import dev.aisdev.example.entities.lesson.LessonVideo
import dev.aisdev.example.ui.base.adapters.BaseMultiTypeAdapter
import dev.aisdev.example.ui.lessonslist.LessonsAdapter

class VideoItemViewHolder(
    itemView: View,
    private val listener: LessonsAdapter.LessonsListener
) : BaseMultiTypeAdapter.ViewHolder<Any>(itemView){

    override fun bindView(item: Any) = (item as LessonVideo).let {
        itemView.run {
            setOnClickListener { listener.onVideoClicked(it.id.toString()) }
        }
    }

}
