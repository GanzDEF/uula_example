package dev.aisdev.example.model.data.server

import androidx.lifecycle.LiveData
import dev.aisdev.example.entities.LessonResponse
import dev.aisdev.example.entities.Resource
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UulaApi {

    @GET("/api/tests/lessons")
    fun getLessonsList(): Deferred<Response<List<LessonResponse>>>

    @GET("/api/tests/lessons")
    fun getLessonsListByPageId(@Query("page") page: Int): Deferred<Response<List<LessonResponse>>>
}

interface UulaLiveDataApi {

    @GET("/api/tests/lessons")
    fun getLessonsList(): LiveData<Resource<List<LessonResponse>>>

    @GET("/api/tests/lessons")
    fun getLessonsListByPageId(@Query("page") page: Int): LiveData<Resource<List<LessonResponse>>>
}
