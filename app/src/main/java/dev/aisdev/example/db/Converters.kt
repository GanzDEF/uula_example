package dev.aisdev.example.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun listToJson(value: List<String>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}