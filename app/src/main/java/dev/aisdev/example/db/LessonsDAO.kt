package dev.aisdev.example.db

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
}
