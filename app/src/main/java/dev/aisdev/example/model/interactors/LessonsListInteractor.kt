package dev.aisdev.example.model.interactors

import dev.aisdev.example.model.reposittories.LessonsNetworkRepository
import javax.inject.Inject

class LessonsListInteractor @Inject constructor(
    private val lessonsListRepository: LessonsNetworkRepository
) {

}