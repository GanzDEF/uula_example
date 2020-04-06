package dev.aisdev.example.di.providers

import android.app.Application
import androidx.room.Room
import dev.aisdev.example.model.data.db.LessonsDatabase
import javax.inject.Inject
import javax.inject.Provider

class DatabaseProvider @Inject constructor(
    private val application: Application
) : Provider<LessonsDatabase> {
    override fun get(): LessonsDatabase = Room
        .databaseBuilder(application, LessonsDatabase::class.java, "lessonsDatabase")
        .build()

}