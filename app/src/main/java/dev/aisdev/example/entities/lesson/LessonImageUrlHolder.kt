package dev.aisdev.example.entities.lesson

import com.google.gson.annotations.SerializedName

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
