package dev.aisdev.example.model.data.db.repositories

import dev.aisdev.example.entities.LessonData
import dev.aisdev.example.model.converters.LessonsApiConverter
import dev.aisdev.example.model.data.db.LessonsDatabase
import dev.aisdev.example.model.data.server.UulaApi
import dev.aisdev.example.model.data.system.SchedulersProvider
import io.reactivex.Single
import javax.inject.Inject

class LessonsRepository @Inject constructor(
    private val api: UulaApi,
    private val converter: LessonsApiConverter,
    private val schedulers: SchedulersProvider,
    private val database: LessonsDatabase
){

    fun getLessonsByPageId(pageId: Int): Single<List<LessonData>> {
        return when {
            getLessonsListFromDatabaseById(pageId).isNullOrEmpty() -> {
                if (pageId == 0) {
                    return getLessonsFromApi()
                        .subscribeOn(schedulers.io())
                        .observeOn(schedulers.ui())
                        .map { list ->
                            if (!list.isNullOrEmpty()) {
                                val newList = mutableListOf<LessonData>()
                                list.map { newList.add(converter.from(it).copy(page_id = pageId)) }
                                Single.create<Unit> { insertLessonsDataToDataBase(newList) }
                                    .subscribeOn(schedulers.io())
                            }
                            return@map getLessonsListFromDatabaseById(pageId)
                        }
                }
                return getLessonsFromApiByPageId(pageId)
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .map { list ->
                        if (!list.isNullOrEmpty()) {
                            val newList = mutableListOf<LessonData>()
                            list.map { newList.add(converter.from(it).copy(page_id = pageId)) }
                            Single.create<Unit> { insertLessonsDataToDataBase(newList) }
                                .subscribeOn(schedulers.io())
                        }
                        return@map getLessonsListFromDatabaseById(pageId)
                    }
            }
            else -> Single.create{ getLessonsListFromDatabaseById(pageId) }
        }
    }

    private fun insertLessonsDataToDataBase(list: List<LessonData>) =
        database.lessonsDAO().insertLessons(list)

    private fun getLessonsListFromDatabaseById(pageId: Int) =
        database.lessonsDAO().getLessons(pageId)

    private fun getLessonsFromApi() =
        api.getLessonsList()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .map { it }

    private fun getLessonsFromApiByPageId(id: Int) =
        api.getLessonsListByPageId(id)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .map { it }
}




