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
) {
    fun lessonResponseToLessonsData(): LessonData {
        val converted = this.lessonData
        converted.copy(kind = this.kind)
        return converted
    }
}




