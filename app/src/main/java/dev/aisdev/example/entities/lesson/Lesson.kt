package dev.aisdev.example.entities.lesson

data class Lesson (
    val id: String,
    val visited: Boolean,
    val title: String,
    val about: String,
    val format: String?,
    val description: String?,
    val duration: String?,
    val file_extension: LessonFileExtension?,
    val images: LessonImageUrlHolder?,
    val comments_count: Int?,
    val question_count: Int?,
    val pageId: Int?,
    val kind: LessonKind?
)
