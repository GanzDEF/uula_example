package dev.aisdev.example.ui.lessonslist

import dev.aisdev.example.R
import dev.aisdev.example.presentation.LessonsListView
import dev.aisdev.example.ui.base.BaseActivity

class LessonsListActivity : BaseActivity(), LessonsListView {

    override val layoutResId: Int
        get() = R.layout.activity_lessons_list


}