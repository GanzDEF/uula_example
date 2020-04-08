package dev.aisdev.example.di.providers

import androidx.room.Room
import dev.aisdev.example.App
import dev.aisdev.example.model.data.db.LessonsDatabase
import javax.inject.Inject
import javax.inject.Provider

class DatabaseProvider @Inject constructor(
    private val application: App
) : Provider<LessonsDatabase> {

    override fun get(): LessonsDatabase = Room
        .databaseBuilder(application, LessonsDatabase::class.java, "lessonsDatabase")
        .build()

}