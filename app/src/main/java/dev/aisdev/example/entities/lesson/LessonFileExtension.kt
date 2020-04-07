package dev.aisdev.example.entities.lesson

import com.google.gson.annotations.SerializedName
import dev.aisdev.example.R

enum class LessonFileExtension(val title: Int) {
    @SerializedName("pdf")
    PDF(R.string.pdf),
    @SerializedName("nil")
    NIL(R.string.nil)
}