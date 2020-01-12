package dev.aisdev.example.presentation

import androidx.lifecycle.ViewModel
import dev.aisdev.example.model.data.db.repositories.LessonsRepository
import javax.inject.Inject

class LessonsListViewModel @Inject constructor(
    private val repo: LessonsRepository
) : ViewModel() {

}
