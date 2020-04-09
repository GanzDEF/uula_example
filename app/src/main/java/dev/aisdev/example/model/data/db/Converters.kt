package dev.aisdev.example.model.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import dev.aisdev.example.entities.lesson.LessonFileExtension
import dev.aisdev.example.entities.lesson.LessonImageUrlHolder
import dev.aisdev.example.entities.lesson.LessonKind

class Converters {


    @TypeConverter
    fun lessonKindToString(value: LessonKind?): String = Gson().toJson(value)

    @TypeConverter
    fun stringToLessonKind(value: String?): LessonKind? =
        Gson().fromJson(value, LessonKind::class.java)

    @TypeConverter
    fun lessonImageToString(value: LessonImageUrlHolder?): String? = Gson().toJson(value)

    @TypeConverter
    fun stringToLessonsImage(value: String?): LessonImageUrlHolder? =
        Gson().fromJson(value, LessonImageUrlHolder::class.java)

    @TypeConverter
    fun lessonsFileExtToString(value: LessonFileExtension?): String? = Gson().toJson(value)

    @TypeConverter
    fun stringToLessonsFileExtensions(value: String?): LessonFileExtension? =
        Gson().fromJson(value, LessonFileExtension::class.java)
}