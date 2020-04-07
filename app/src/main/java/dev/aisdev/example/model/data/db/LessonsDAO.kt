package dev.aisdev.example.model.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.aisdev.example.entities.lesson.LessonData

@Dao
interface LessonsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLessons(vararg: List<LessonData>)

    @Query("SELECT * FROM lessons WHERE page_id = :pageId")
    fun getLessons(pageId: Int): List<LessonData>

    @Query("DELETE FROM lessons")
    fun deleteAllLessons()

    @Query("SELECT count(id) FROM lessons")
    fun getlessonsSize(): Int
}
