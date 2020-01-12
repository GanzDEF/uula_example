package dev.aisdev.example.db.repositories

import dev.aisdev.example.db.Converters
import dev.aisdev.example.db.LessonsDatabase
import dev.aisdev.example.entities.LessonData
import dev.aisdev.example.model.data.server.UulaApi
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LessonsRepository  {

    @Inject lateinit var webApi: UulaApi

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    @Inject lateinit var lessonsDatabase: LessonsDatabase

    fun getAllLessons(
        result: (lessonsList: List<LessonData>) -> Unit
    ) {
        scope.launch {
            val lessons = mutableListOf<LessonData>()
            val response = webApi.getLessonsList().await()
            if (response.isSuccessful) {
                response.body()?.let { lessons.addAll(it.asSequence().map { resp -> resp.lessonResponseToLessonsData() }) }
            }
            result.invoke(lessons)
        }
    }

    fun getLessonsByPage(
        page: Int,
        result: (lessonsList: List<LessonData>) -> Unit
    ) {
        val lessons = mutableListOf<LessonData>()
        val jobs = mutableListOf<Job>()
        val job = scope.launch {
            val response = webApi.getLessonsListByPageId(page).await()
            if (response.isSuccessful) {
                response.body()?.let { lessons.addAll(it.asSequence().map { resp -> resp.lessonResponseToLessonsData() }) }
            }
        }
        jobs += job
        result.invoke(lessons)
    }

    fun insertLessons(lessonsRes: List<LessonData>, complete: () -> Unit) {
        GlobalScope.launch {
            lessonsDatabase.lessonsDAO().insertLessons(lessonsRes)
            complete.invoke()
        }
    }

    fun clearDb(complete: () -> Unit) {
        GlobalScope.launch {
            val job = GlobalScope.launch {
                lessonsDatabase.lessonsDAO().deleteAllLessons()
            }
            job.join()
            complete.invoke()
        }
    }

    fun isUpdatable(result: (isNeed: Boolean) -> Unit) {
        GlobalScope.launch {
            with(lessonsDatabase.lessonsDAO()) {
                val isNeeded = getlessonsSize() == 0
                result.invoke(isNeeded)

            }
        }
    }

    fun getLessons(result: (lessonsList: List<LessonData>) -> Unit) {
        GlobalScope.launch {
            val lessons = lessonsDatabase.lessonsDAO().getLessons()
            result.invoke(lessons)
        }
    }

}


