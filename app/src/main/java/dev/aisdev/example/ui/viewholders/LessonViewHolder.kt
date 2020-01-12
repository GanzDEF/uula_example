package dev.aisdev.example.ui.viewholders

import android.view.View
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import dev.aisdev.example.databinding.ViewLessonDetailBinding
import dev.aisdev.example.entities.LessonData
import dev.aisdev.example.entities.LessonKind
import dev.aisdev.example.entities.LessonResponse
import dev.aisdev.example.extentions.visible
import kotlinx.android.synthetic.main.view_lesson_detail.view.*

class LessonViewHolder(view: View) : BaseViewHolder(view) {

    interface Delegate {
        fun onLessonClicked(lesson: LessonResponse)
    }

    private lateinit var lessonData: LessonData
    private val binding by lazy {
        DataBindingUtil.bind<ViewLessonDetailBinding>(view)
    }

    override fun bindData(data: Any) {
        if (data is LessonData) {
            lessonData = data
            drawLessonCard()
        }
    }

    private fun drawLessonCard() {
        itemView.run {
            binding?.lessonData = lessonData
            ivIsVisited.ivIsVisited.visible(lessonData.visited)
            when (lessonData.kind) {
                LessonKind.SURVEY, LessonKind.OFFLINE_MATERIAL -> {
                    clVideoHolder.visible(false)
                    cvMaterialsHolder.visible(true)

                }
                LessonKind.VIDEO -> {
                    clVideoHolder.visible(true)
                    cvMaterialsHolder.visible(false)

                    Glide.with(context)
                        .load(lessonData.images?.small)
                        .into(ivVideoPreview)
                }
            }
            binding?.executePendingBindings()
        }
    }

    override fun onClick(v: View?) = Unit // for now

    override fun onLongClick(v: View?): Boolean = false // for now

}