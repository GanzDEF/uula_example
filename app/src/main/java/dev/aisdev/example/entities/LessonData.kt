package dev.aisdev.example.entities

import com.google.gson.annotations.SerializedName
import dev.aisdev.example.R

data class LessonData(
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
    @SerializedName("file_extension")
    val file_extension: LessonFileExtension?,
    @SerializedName("duration")
    val duration: Long?,
    @SerializedName("images")
    val images: LessonImageUrlHolder?,
    @SerializedName("comments_count")
    val comments_count: Int?,
    @SerializedName("question_count")
    val question_count: Int?
)

enum class LessonFileExtension(val title: Int) {
    @SerializedName("pdf")
    PDF(R.string.pdf),
    @SerializedName("nil")
    NIL(R.string.nil)
}

data class LessonImageUrlHolder(
    @SerializedName("large")
    val large: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("xlarge")
    val xlarge: String,
    @SerializedName("xsmall")
    val xsmall: String,
    @SerializedName("xxlarge")
    val xxlarge: String
)