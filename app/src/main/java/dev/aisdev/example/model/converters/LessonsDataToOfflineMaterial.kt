package dev.aisdev.example.model.converters

import dev.aisdev.example.entities.lesson.LessonData
import dev.aisdev.example.entities.lesson.LessonOfflineMaterial

class LessonsDataToOfflineMaterial : Converter<LessonData, LessonOfflineMaterial> {

    override fun from(from: LessonData) = LessonOfflineMaterial(
        id = from.id,
        visited = from.visited,
        title = from.title,
        about = from.about,
        format = from.format,
        file_extension = from.file_extension
    )
}