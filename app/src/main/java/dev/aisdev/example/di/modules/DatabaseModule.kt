package dev.aisdev.example.di.modules

import dev.aisdev.example.di.providers.DatabaseProvider
import dev.aisdev.example.model.data.db.LessonsDatabase
import toothpick.config.Module
import javax.inject.Inject

class DatabaseModule @Inject constructor() : Module() {

    init {
        bind(LessonsDatabase::class.java).toProvider(DatabaseProvider::class.java).providesSingleton()
    }
}