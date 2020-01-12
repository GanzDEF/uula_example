package dev.aisdev.example.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.aisdev.example.entities.LessonResponse

@Dao
interface LessonsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLessons(vararg: List<LessonResponse>)

    @Query("SELECT * FROM lessons")
    fun getLessons(): List<LessonResponse>

    @Query("DELETE FROM lessons")
    fun deleteAllLessons()

    @Query("SELECT count(lessonData) FROM lessons")
    fun getlessonsSize(): Int
}

@Dao
interface LessonsLiveDataDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLessons(vararg: List<LessonResponse>)

    @Query("SELECT * FROM lessons")
    fun getLessons(): LiveData<List<LessonResponse>>

    @Query("DELETE FROM lessons")
    fun deleteAllLessons()

    @Query("SELECT count(lessonData) FROM lessons")
    fun getlessonsSize(): Int
}