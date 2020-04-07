package dev.aisdev.example.di.modules

import android.content.Context
import android.content.SharedPreferences
import dev.aisdev.example.di.SharedPrefsName
import dev.aisdev.example.di.providers.SharedPrefsProvider
import dev.aisdev.example.model.data.system.AppSchedulers
import dev.aisdev.example.model.data.system.ResourceManager
import dev.aisdev.example.model.data.system.SchedulersProvider
import toothpick.config.Module

class AppModule(context: Context) : Module() {

    companion object {
        private const val SharedPreferencesName = "uulaSharedPrefs"
    }

    init {
        bind(Context::class.java).toInstance(context)
        bind(SchedulersProvider::class.java).toInstance(AppSchedulers())
        bind(ResourceManager::class.java).singleton()

        // Shared preferences name
        bind(String::class.java).withName(SharedPrefsName::class.java).toInstance(SharedPreferencesName)

        // SharedPreferences
        bind(SharedPreferences::class.java).toProvider(SharedPrefsProvider::class.java).providesSingleton()
    }
}