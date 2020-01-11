package dev.aisdev.example.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.aisdev.example.entities.LessonResponse

@Database(entities = [LessonResponse::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LessonsDatabase : RoomDatabase() {
    abstract fun LessonsDAO(): LessonsDAO
}