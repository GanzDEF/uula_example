package dev.aisdev.example.entities

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "lessons")
data class LessonResponse(
    @SerializedName("kind")
    @Expose
    val kind: LessonKind,
    @SerializedName("item")
    @Expose
    val lessonData: LessonData
    )




