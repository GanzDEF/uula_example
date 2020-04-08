package dev.aisdev.example.entities.lesson

data class LessonsScreenWrapper(
    val stageId: Int?,
    val stageTitle: String,
    val stageMessage: String,
    val stageList: List<LessonsListItem>
)

data class LessonHeader(
    var title: String
) : LessonsListItem

data class LessonVideo(
    val id: String,
    val visited: Boolean,
    val title: String,
    val about: String,
    val images: LessonImageUrlHolder?,
    val duration: String?,
    val comments_count: Int?,
    val description: String
) : LessonsListItem

data class LessonSurvey(
    val id: String,
    val visited: Boolean,
    val title: String,
    val about: String,
    val question_count: Int?
) : LessonsListItem

data class LessonOfflineMaterial(
    val id: String,
    val visited: Boolean,
    val title: String,
    val about: String,
    val format: String?,
    val file_extension: LessonFileExtension?
) : LessonsListItem

data class LessonFooter(
    var prev: Boolean,
    var next: Boolean
)

interface LessonsListItem

