package dev.aisdev.example.model.data.server

import dev.aisdev.example.entities.LessonResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UulaApi {

    @GET("/api/tests/lessons")
    suspend fun getLessonsList(): Deferred<Response<List<LessonResponse>>>

    @GET("/api/tests/lessons")
    suspend fun getLessonsListByPageId(@Query("page") page: Int): Deferred<Response<List<LessonResponse>>>
}
