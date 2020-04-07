package dev.aisdev.example.entities.lesson

import com.google.gson.annotations.SerializedName
import dev.aisdev.example.R

enum class LessonKind(val innerContent: String, val image: Int) {
    @SerializedName("Video")
    VIDEO("Comments", R.drawable.play_button),
    @SerializedName("Survey")
    SURVEY("Questions", R.drawable.quiz_icon),
    @SerializedName("OfflineMaterial")
    OFFLINE_MATERIAL("Offline Material", R.drawable.pdf_icon)
}
