package dev.aisdev.example.model.converters

import dev.aisdev.example.entities.lesson.LessonData
import dev.aisdev.example.entities.lesson.LessonSurvey

class LessonsDataToSurveyConverter : Converter<LessonData, LessonSurvey> {
    override fun from(from: LessonData): LessonSurvey = LessonSurvey(
        id = from.id,
        visited = from.visited,
        title = from.title,
        about = from.about,
        question_count = from.question_count
    )
}