package dev.aisdev.example.model.data.system

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceManager @Inject constructor(private val context: Context) {

    fun getString(resId: Int): String = context.getString(resId)
    fun getString(@StringRes resId: Int, vararg formatArgs: Any?) = context.getString(resId, *formatArgs)
    fun getApplicationContext(): Context = context.applicationContext
}