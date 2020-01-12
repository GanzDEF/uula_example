package dev.aisdev.example.ui.viewholders

import android.view.View
import androidx.databinding.DataBindingUtil
import dev.aisdev.example.entities.LessonData
import dev.aisdev.example.entities.LessonResponse

class LessonViewHolder(view: View) : BaseViewHolder(view) {

    interface Delegate {
        fun onLessonClicked(lesson: LessonResponse)
    }

    private lateinit var lessonData: LessonData
//    private val binding by lazy {
//        DataBindingUtil.bind<>(view)
//    }
//
    override fun bindData(data: Any) {
        if (data is LessonData) {
            lessonData = data
            drawUserCard()
        }
    }

    private fun drawUserCard() {
        itemView.run {

        }

    }

    override fun onClick(v: View?) = Unit // for now

    override fun onLongClick(v: View?): Boolean = false // for now

}