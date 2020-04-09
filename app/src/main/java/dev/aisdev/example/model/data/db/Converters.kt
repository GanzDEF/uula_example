package dev.aisdev.example.model.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.aisdev.example.entities.lesson.*
import java.util.*

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