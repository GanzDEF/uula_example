package dev.aisdev.example.ui.lessonslist.viewHolders

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import androidx.core.content.ContextCompat
import dev.aisdev.example.R
import dev.aisdev.example.entities.lesson.LessonOfflineMaterial
import dev.aisdev.example.extentions.visible
import dev.aisdev.example.ui.base.adapters.BaseMultiTypeAdapter
import dev.aisdev.example.ui.lessonslist.LessonsAdapter
import kotlinx.android.synthetic.main.view_lesson_offline_material_detail.view.*

class OfflineMaterialItemViewHolder(
    itemView: View,
    private val listener: LessonsAdapter.LessonsListener
) : BaseMultiTypeAdapter.ViewHolder<Any>(itemView){

    override fun bindView(item: Any) = (item as LessonOfflineMaterial).let {
        itemView.run {
            setOnClickListener { listener.onSurveyClicked(it.id.toString()) }
            tvLessonTitle.text = it.title
            ivIconContent.setImageDrawable(context.getDrawable(R.drawable.file_icon))
            ivIconContent.colorFilter = PorterDuffColorFilter(
                ContextCompat.getColor(context, R.color.colorTextGrey),
                PorterDuff.Mode.SRC_ATOP
            )
            tvCountContentItems.text = String.format("%s", it.format.toString())
            ivIsVisited.visible(it.visited)
        }
    }

}
