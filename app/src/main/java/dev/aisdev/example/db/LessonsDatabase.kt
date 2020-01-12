package dev.aisdev.example.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.aisdev.example.entities.LessonData

@Database(entities = [LessonData::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LessonsDatabase : RoomDatabase() {
    abstract fun lessonsDAO(): LessonsDAO
}