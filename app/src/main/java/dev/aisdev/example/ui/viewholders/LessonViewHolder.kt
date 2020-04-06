package dev.aisdev.example.ui.viewholders

import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import dev.aisdev.example.R
import dev.aisdev.example.databinding.ViewLessonDetailBinding
import dev.aisdev.example.entities.LessonData
import dev.aisdev.example.entities.LessonKind
import dev.aisdev.example.extentions.visible
import kotlinx.android.synthetic.main.view_lesson_detail.view.*

class LessonViewHolder(
    private val view: View,
    private val delegate: Delegate
) : BaseViewHolder(view) {

    interface Delegate {
        fun onLessonClicked(lesson: LessonData)
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
                    when (lessonData.kind) {
                        LessonKind.SURVEY -> clVideoHolder.setCardBackgroundColor(ContextCompat.getColor(context, R.color.cardview_shadow_end_color))
                        LessonKind.OFFLINE_MATERIAL -> clVideoHolder.setCardBackgroundColor(ContextCompat.getColor(context, R.color.cardview_shadow_start_color))
                    }

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

    override fun onClick(v: View?) = delegate.onLessonClicked(lessonData)

    override fun onLongClick(v: View?): Boolean = false // for now

}