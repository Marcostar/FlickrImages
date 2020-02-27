package com.example.thecolorado.Converters

import androidx.room.TypeConverter
import com.example.thecolorado.data.Media
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MediaConverter {

    @TypeConverter
    fun fromMediaToJson(stat: Media): String {
        return Gson().toJson(stat)
    }

    /**
     * Convert a json to a list of Images
     */
    @TypeConverter
    fun fromJsonToMedia(jsonImages: String): Media {
        val type = object : TypeToken<Media>() {}.type
        return Gson().fromJson<Media>(jsonImages, type)
    }
}