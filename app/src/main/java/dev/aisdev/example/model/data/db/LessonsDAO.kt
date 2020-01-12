package dev.aisdev.example.model.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.aisdev.example.entities.LessonData

@Dao
interface LessonsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLessons(vararg: List<LessonData>)

    @Query("SELECT * FROM lessons")
    fun getLessons(): List<LessonData>

    @Query("DELETE FROM lessons")
    fun deleteAllLessons()

    @Query("SELECT count(id) FROM lessons")
    fun getlessonsSize(): Int
}

@Dao
interface LessonsLiveDataDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLessons(vararg: List<LessonData>)

    @Query("SELECT * FROM lessons")
    fun getLessons(): LiveData<List<LessonData>>

    @Query("DELETE FROM lessons")
    fun deleteAllLessons()

    @Query("SELECT count(id) FROM lessons")
    fun getlessonsSize(): Int
}