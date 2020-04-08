package dev.aisdev.example.model.converters

import dev.aisdev.example.entities.lesson.LessonData
import dev.aisdev.example.entities.lesson.LessonVideo

class LessonDataToVideoConverter : Converter<LessonData, LessonVideo> {

    override fun from(from: LessonData) = LessonVideo(
        id = from.id,
        visited = from.visited,
        title = from.title,
        about = from.about,
        images = from.images,
        duration = from.duration,
        comments_count = from.comments_count,
        description = from.description ?: ""
    )
}