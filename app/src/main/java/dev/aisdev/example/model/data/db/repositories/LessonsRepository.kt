package dev.aisdev.example.model.data.db.repositories

import dev.aisdev.example.entities.lesson.LessonData
import dev.aisdev.example.model.converters.LessonsApiConverter
import dev.aisdev.example.model.data.db.LessonsDatabase
import dev.aisdev.example.model.data.server.UulaApi
import dev.aisdev.example.model.data.system.SchedulersProvider
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LessonsRepository @Inject constructor(
    private val api: UulaApi,
    private val converter: LessonsApiConverter,
    private val schedulers: SchedulersProvider,
    private val database: LessonsDatabase
){

    fun getLessonsByPageId(pageId: Int): Observable<List<LessonData>> = when (pageId) {
        0 -> getLessons()
        else -> getLessonsById(pageId)
    }

    private fun getLessons(): Observable<List<LessonData>> =
        Observable.concatArrayEager(
        getLessonsListFromDatabaseById(0),
        getLessonsFromApi()
            .materialize()
            .observeOn(schedulers.ui())
            .map { it }
            .filter{ !it.isOnError }
            .dematerialize<List<LessonData>>()
            .debounce(400, TimeUnit.MILLISECONDS))

    private fun getLessonsById(pageId: Int): Observable<List<LessonData>> =
        Observable.concatArrayEager(
            getLessonsListFromDatabaseById(0),
            getLessonsFromApiByPageId(pageId)
                .materialize()
                .observeOn(schedulers.ui())
                .map { it }
                .filter{ !it.isOnError }
                .dematerialize<List<LessonData>>()
                .debounce(400, TimeUnit.MILLISECONDS))

    private fun getLessonsListFromDatabaseById(pageId: Int): Observable<List<LessonData>> =
        database.lessonsDAO().getLessons(pageId)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .filter { it.isNotEmpty() }
            .toObservable()

    private fun getLessonsFromApi(): Observable<List<LessonData>> =
        api.getLessonsList()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .map {
                storeUserInDb(it.map { lessons -> converter.from(lessons) })
                it.map { lesson -> converter.from(lesson) }
            }

    private fun storeUserInDb(list: List<LessonData>) {
        Observable.fromCallable { database.lessonsDAO().insertLessons(list) }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.io())
            .subscribe()
    }

    private fun getLessonsFromApiByPageId(id: Int): Observable<List<LessonData>> =
        api.getLessonsListByPageId(id)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .map {
                storeUserInDb(it.map { lessons -> converter.from(lessons) })
                it.map { lesson -> converter.from(lesson) }
            }
}




