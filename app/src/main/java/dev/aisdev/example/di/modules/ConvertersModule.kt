package dev.aisdev.example.di.modules

import dev.aisdev.example.model.converters.LessonDataToVideoConverter
import dev.aisdev.example.model.converters.LessonsApiConverter
import dev.aisdev.example.model.converters.LessonsDataToOfflineMaterial
import dev.aisdev.example.model.converters.LessonsDataToSurveyConverter
import toothpick.config.Module
import javax.inject.Inject

class ConvertersModule @Inject constructor() : Module() {

    init {
        bind(LessonsApiConverter::class.java).toInstance(LessonsApiConverter())
        bind(LessonsDataToOfflineMaterial::class.java).toInstance(LessonsDataToOfflineMaterial())
        bind(LessonsDataToSurveyConverter::class.java).toInstance(LessonsDataToSurveyConverter())
        bind(LessonDataToVideoConverter::class.java).toInstance(LessonDataToVideoConverter())
    }
}