package dev.aisdev.example.ui.lessonslist

import dev.aisdev.example.R
import dev.aisdev.example.entities.lesson.*
import dev.aisdev.example.ui.base.adapters.BasePaginationAdapter
import dev.aisdev.example.ui.base.adapters.viewHolderProvider
import dev.aisdev.example.ui.lessonslist.viewHolders.*

class LessonsAdapter(listener: LessonsListener) : BasePaginationAdapter<Any>(
    list = listOf(),
    providers = mapOf(
        VIDEO_ITEM_LAYOUT to viewHolderProvider { VideoItemViewHolder(it, listener) },
        OFFLINE_MATERIAL_ITEM_LAYOUT to viewHolderProvider { OfflineMaterialItemViewHolder(it, listener) },
        SURVEY_ITEM_LAYOUT to viewHolderProvider { SurveyItemViewHolder(it, listener) },
        HEADER_ITEM_LAYOUT to viewHolderProvider { HeaderItemViewHolder(it) },
        FOOTER_ITEM_LAYOUT to viewHolderProvider { FooterItemViewHolder(it, listener) }
    ),
    typeSelector = {
        when (it) {
            is LessonVideo -> VIDEO_ITEM_LAYOUT
            is LessonSurvey -> SURVEY_ITEM_LAYOUT
            is LessonOfflineMaterial -> OFFLINE_MATERIAL_ITEM_LAYOUT
            is LessonHeader -> HEADER_ITEM_LAYOUT
            is LessonFooter -> FOOTER_ITEM_LAYOUT
            else -> -1
        }
    }
){

    companion object {
        const val VIDEO_ITEM_LAYOUT = R.layout.view_lesson_video_detail
        const val OFFLINE_MATERIAL_ITEM_LAYOUT = R.layout.view_lesson_offline_material_detail
        const val SURVEY_ITEM_LAYOUT = R.layout.view_lesson_survey_detail
        const val HEADER_ITEM_LAYOUT = R.layout.view_lesson_header_detail
        const val FOOTER_ITEM_LAYOUT = R.layout.view_lesson_navigation
    }

    interface LessonsListener {
        fun onVideoClicked(itemId: String)
        fun onOfflineMaterialClicked(itemId: String)
        fun onSurveyClicked(itemId: String)
        fun onPrevClicked()
        fun onNextClicked()
    }

}