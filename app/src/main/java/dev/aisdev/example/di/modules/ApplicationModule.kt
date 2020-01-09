package dev.aisdev.example.di.modules

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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

}