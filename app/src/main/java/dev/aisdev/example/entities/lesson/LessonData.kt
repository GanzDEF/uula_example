package dev.aisdev.example.entities.lesson

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import dev.aisdev.example.model.data.db.Converters

@Entity(tableName = "lessons")
data class LessonData(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("visited")
    val visited: Boolean? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("about")
    val about: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("format")
    val format: String? = null,
    @SerializedName("duration")
    val duration: String? = null,
    @TypeConverters(Converters::class)
    @SerializedName("file_extension")
    val file_extension: LessonFileExtension? = null,
    @SerializedName("images")
    val images: LessonImageUrlHolder? = null,
    @SerializedName("comments_count")
    val comments_count: Int? = null,
    @SerializedName("question_count")
    val question_count: Int? = null,
    var page_id: Int? = null,
    @TypeConverters(Converters::class)
    val kind: LessonKind? = null
)

