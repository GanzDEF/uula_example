package dev.aisdev.example.ui.viewholders

import android.view.View
import dev.aisdev.example.entities.LessonData

class LessonViewHolder(view: View) : BaseViewHolder(view) {

    private lateinit var lessonData: LessonData
    override fun bindData(data: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(v: View?) = Unit // for now

    override fun onLongClick(v: View?): Boolean = false // for now

}