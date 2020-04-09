package dev.aisdev.example.model.converters

import dev.aisdev.example.entities.lesson.LessonData
import dev.aisdev.example.entities.lesson.LessonFileExtension
import dev.aisdev.example.entities.lesson.LessonResponse

class LessonsApiConverter : Converter<LessonResponse, LessonData>{

    override fun from(from: LessonResponse) = LessonData(
            id = from.lessonData?.id ?: "0",
            visited = from.lessonData?.visited ?: false,
            title = from.lessonData?.title ?: "",
            about = from.lessonData?.about,
            format = from.lessonData?.format,
            duration = from.lessonData?.duration,
            file_extension = from.lessonData?.file_extension ?: LessonFileExtension.NIL,
            images = from.lessonData?.images,
            description = from.lessonData?.description,
            comments_count = from.lessonData?.comments_count,
            question_count = from.lessonData?.question_count,
            page_id = 0,
            kind = from.kind
        )

        override fun to(from: LessonData) =  LessonResponse(
                kind = from.kind,
                lessonData = LessonData(
                        id = from.id,
                        visited = from.visited ?: false,
                        title = from.title ?: "",
                        about = from.about,
                        format = from.format,
                        duration = from.duration,
                        file_extension = from.file_extension ?: LessonFileExtension.NIL,
                        images = from.images,
                        description = from.description,
                        comments_count = from.comments_count,
                        question_count = from.question_count,
                        page_id = 0,
                        kind = from.kind
                )
        )
}
