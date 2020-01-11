package dev.aisdev.example.entities

import com.google.gson.annotations.SerializedName

enum class LessonKind {
    @SerializedName("Video")
    VIDEO,
    @SerializedName("Survey")
    SURVEY,
    @SerializedName("OfflineMaterial")
    OFFLINE_MATERIAL
}
