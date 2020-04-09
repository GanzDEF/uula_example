package dev.aisdev.example.model.data.server

import dev.aisdev.example.entities.lesson.LessonResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface UulaApi {

    @GET("/api/tests/lessons")
    fun getLessonsList(): Observable<List<LessonResponse>>

    @GET("/api/tests/lessons")
    fun getLessonsListByPageId(@Query("page") page: Int): Observable<List<LessonResponse>>
}


