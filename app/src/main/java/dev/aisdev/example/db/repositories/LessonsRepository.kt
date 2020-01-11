package dev.aisdev.example.db.repositories

import dev.aisdev.example.db.LessonsDatabase
import dev.aisdev.example.entities.LessonResponse
import dev.aisdev.example.model.data.server.UulaApi
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

object LessonsRepository  {

    @Inject private lateinit var webApi: UulaApi

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    @Inject private lateinit var lessonsDatabase: LessonsDatabase

    fun getAllLessons(
        result: (lessonsList: List<LessonResponse>) -> Unit
    ) {
        scope.launch {
            val lessons = mutableListOf<LessonResponse>()
            val response = webApi.getLessonsList().await()
            if (response.isSuccessful) {
                response.body()?.let { lessons.addAll(it) }
            }
            result.invoke(lessons)
        }
    }
    fun getLessonsByPage(
        page: Int,
        result: (lessonsList: List<LessonResponse>) -> Unit
    ) {
        val lessons = mutableListOf<LessonResponse>()
        val jobs = mutableListOf<Job>()
        val job = scope.launch {
            val response = webApi.getLessonsListByPageId(page).await()
            if (response.isSuccessful) {
                response.body()?.let { lessons.addAll(it) }
            }
        }
        jobs += job
        result.invoke(lessons)
    }

    fun insertLessons(lessonsRes: List<LessonResponse>, complete: () -> Unit) {
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

    fun getLessons(result: (lessonsList: List<LessonResponse>) -> Unit) {
        GlobalScope.launch {
            val lessons = lessonsDatabase.lessonsDAO().getLessons()
            result.invoke(lessons)
        }
    }

}


