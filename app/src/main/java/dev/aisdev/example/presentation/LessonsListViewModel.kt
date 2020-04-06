package dev.aisdev.example.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.aisdev.example.entities.FetchStatus
import dev.aisdev.example.entities.LessonData
import dev.aisdev.example.entities.Resource
import dev.aisdev.example.model.data.db.repositories.LessonsRepository
import javax.inject.Inject

class LessonsListViewModel @Inject constructor(
//    private val repo: LessonsRepository
) : ViewModel() {

    val title = "Migration"
    val subtitle = "English Language - Grade 12 - Semester 1"

    private val lessons: MutableLiveData<String> = MutableLiveData()

//    val lessonsLiveData: LiveData<Resource<LessonData>>

    var fetchStatus = FetchStatus()
        private set

    init {


//        lessonsLiveData = repo.getAllLessons { }
    }
}
