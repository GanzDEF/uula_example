package dev.aisdev.example.model.converters

import dev.aisdev.example.entities.LessonData
import dev.aisdev.example.entities.LessonResponse

class LessonsApiConverter : Converter<LessonResponse, LessonData>{
    override fun from(from: LessonResponse) =
        LessonData(
            id = from.lessonData.id,
            visited = from.lessonData.visited,
            title = from.lessonData.title,
            about = from.lessonData.about,
            format = from.lessonData.format,
            duration = from.lessonData.duration,
            file_extension = from.lessonData.file_extension,
            images = from.lessonData.images,
            comments_count = from.lessonData.comments_count,
            question_count = from.lessonData.question_count,
            page_id = 0,
            kind = from.kind
        )

}
