package dev.aisdev.example.model.interactors

import dev.aisdev.example.db.repositories.LessonsRepository
import javax.inject.Inject

class LessonsListInteractor @Inject constructor(
    private val lessonsListRepository: LessonsRepository
) {

}