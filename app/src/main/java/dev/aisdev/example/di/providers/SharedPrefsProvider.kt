package dev.aisdev.example.di.providers

import android.content.Context
import android.content.SharedPreferences
import dev.aisdev.example.di.SharedPrefsName
import javax.inject.Inject
import javax.inject.Provider

class SharedPrefsProvider @Inject constructor(
    private val context: Context,
    @SharedPrefsName private val prefsName: String
) : Provider<SharedPreferences> {

    override fun get(): SharedPreferences {
        return context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    }
}