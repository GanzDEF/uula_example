package dev.aisdev.example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dev.aisdev.example.R
import dev.aisdev.example.databinding.ActivityLessonsListBinding
import dev.aisdev.example.entities.LessonData
import dev.aisdev.example.extentions.vmps
import dev.aisdev.example.presentation.LessonsListViewModel
import dev.aisdev.example.presentation.ViewModelProviderFactory
import dev.aisdev.example.ui.adapters.LessonsDataAdapter
import dev.aisdev.example.ui.adapters.RecyclerViewPaginator
import dev.aisdev.example.ui.viewholders.LessonViewHolder
import javax.inject.Inject

class LessonsListActivity : AppCompatActivity(), LessonViewHolder.Delegate {

    @Inject lateinit var viewModelFactory: ViewModelProviderFactory
    private val viewModel by lazy {
        vmps(viewModelFactory, LessonsListViewModel::class)
    }
    private lateinit var binding: ActivityLessonsListBinding
    private lateinit var paginator: RecyclerViewPaginator
    private val adapter by lazy { LessonsDataAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_lessons_list)
        binding.viewModel = viewModel

    }

    override fun onLessonClicked(lesson: LessonData) {

    }
}
