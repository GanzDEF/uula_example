package dev.aisdev.example.entities.lesson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LessonResponse(
    @SerializedName("kind")
    @Expose
    val kind: LessonKind?,
    @SerializedName("item")
    @Expose
    val lessonData: LessonData?
)




