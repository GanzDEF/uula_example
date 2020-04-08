package dev.aisdev.example.ui.lessonslist

import android.os.Bundle
import dev.aisdev.example.R
import dev.aisdev.example.extentions.addSystemBottomPadding
import dev.aisdev.example.presentation.LessonsListView
import dev.aisdev.example.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_lessons_list.*
import kotlinx.android.synthetic.main.layout_base_list.*

class LessonsListActivity : BaseActivity(), LessonsListView {

    override val layoutResId: Int
        get() = R.layout.activity_lessons_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rlRefresh.addSystemBottomPadding()
        tvLessonsListTitle.text = getString(R.string.activity_lessons_title)
        tvLessonsListDescription.text = getString(R.string.lesson_activity_text)
    }

}