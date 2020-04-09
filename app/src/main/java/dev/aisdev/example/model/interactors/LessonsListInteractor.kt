package dev.aisdev.example.model.interactors

import dev.aisdev.example.entities.lesson.LessonData
import dev.aisdev.example.model.converters.LessonsApiConverter
import dev.aisdev.example.model.data.db.repositories.LessonsRepository
import dev.aisdev.example.model.data.system.SchedulersProvider
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class LessonsListInteractor @Inject constructor(
    private val lessonsListRepository: LessonsRepository
) {

    fun getLessonsList(pageId: Int = 0): Observable<List<LessonData>> =
        lessonsListRepository.getLessonsByPageId(pageId)

}




