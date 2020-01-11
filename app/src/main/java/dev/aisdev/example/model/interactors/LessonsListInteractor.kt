package dev.aisdev.example.model.interactors

import dev.aisdev.example.db.repositories.LessonsRoomRepository
import javax.inject.Inject

class LessonsListInteractor @Inject constructor(
    private val lessonsListRepository: LessonsRoomRepository
) {

}