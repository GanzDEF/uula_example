package dev.aisdev.example.di.modules

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dev.aisdev.example.model.data.db.LessonsDatabase
import javax.inject.Singleton

private const val DB_NAME = "lessonsDatabase"

@Module
class ApplicationModule(var context: Context) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesLessonsDatabase(): LessonsDatabase =
        Room.databaseBuilder(
            context,
            LessonsDatabase::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

}