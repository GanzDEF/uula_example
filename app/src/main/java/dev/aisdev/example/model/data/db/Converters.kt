package dev.aisdev.example.model.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.aisdev.example.entities.lesson.*
import java.util.*

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun listToJson(value: List<String>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun lessonResponseToLessonsData(value: LessonResponse): LessonData {
        val converted = value.lessonData
        converted.copy(kind = value.kind)
        return converted
    }
    @TypeConverter
    fun lessonKindToString(value: LessonKind): String =
        value.name.toLowerCase(Locale.getDefault()).capitalize()

    @TypeConverter
    fun stringToLessonKind(value: String) =
        Gson().fromJson<LessonKind>(value, LessonKind::class.java)

    @TypeConverter
    fun LessonImageToString(value: LessonImageUrlHolder) = value.large

    @TypeConverter
    fun stringToLessonsImage(value: String) =
        LessonImageUrlHolder(
            large = value,
            medium = value,
            small = value,
            xlarge = value,
            xsmall = value,
            xxlarge = value
        )

    @TypeConverter
    fun lessonsFileExtToString(value: LessonFileExtension) =
        value.name

    @TypeConverter
    fun stringToLessonsFileExtensions(value: String) =
        Gson().fromJson(value, LessonFileExtension::class.java)
}