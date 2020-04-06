package dev.aisdev.example.ui.adapters

import android.view.View
import dev.aisdev.example.R
import dev.aisdev.example.entities.LessonData
import dev.aisdev.example.ui.viewholders.BaseViewHolder
import dev.aisdev.example.ui.viewholders.LessonViewHolder


class LessonsDataAdapter(
    private val delegate: LessonViewHolder.Delegate
) : BaseRecyclerViewAdapter() {

    override fun layout(sectionRow: SectionRow) =
        R.layout.view_lesson_detail

    override fun viewHolder(layout: Int, view: View): BaseViewHolder =
        LessonViewHolder(view, delegate)

    fun updateLessonsList(lessons: List<LessonData>) {
        sections()[0].addAll(lessons)
        notifyDataSetChanged()
    }

    // I don't need DiffUtils there for now
}
