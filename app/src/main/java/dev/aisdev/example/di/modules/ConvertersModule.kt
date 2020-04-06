package dev.aisdev.example.di.modules

import dev.aisdev.example.model.converters.LessonsApiConverter
import toothpick.config.Module
import javax.inject.Inject

class ConvertersModule @Inject constructor() : Module() {

    init {
        bind(LessonsApiConverter::class.java).toInstance(LessonsApiConverter())
    }
}