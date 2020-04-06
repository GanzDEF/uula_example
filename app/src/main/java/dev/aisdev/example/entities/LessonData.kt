package dev.aisdev.example.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "lessons")
data class LessonData(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("visited")
    val visited: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("about")
    val about: String,
    @SerializedName("format")
    val format: String?,
    @SerializedName("duration")
    val duration: String?,
    @SerializedName("file_extension")
    val file_extension: LessonFileExtension?,
    @SerializedName("images")
    val images: LessonImageUrlHolder?,
    @SerializedName("comments_count")
    val comments_count: Int?,
    @SerializedName("question_count")
    val question_count: Int?,
    var page_id: Int?,
    val kind: LessonKind
)

