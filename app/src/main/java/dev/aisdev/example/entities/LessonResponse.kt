package dev.aisdev.example.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LessonResponse(
    @SerializedName("kind")
    @Expose
    val kind: LessonKind,
    @SerializedName("item")
    @Expose
    val lessonData: LessonData
    )

enum class LessonKind {
    @SerializedName("Video")
    VIDEO,
    @SerializedName("Survey")
    SURVEY,
    @SerializedName("OfflineMaterial")
    OFFLINE_MATERIAL
}



