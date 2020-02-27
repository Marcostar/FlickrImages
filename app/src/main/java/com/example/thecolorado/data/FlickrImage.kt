package com.example.thecolorado.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.thecolorado.Converters.MediaConverter


@Entity(tableName = "FlickerImage")
data class FlickrImage(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    @TypeConverters(MediaConverter::class)
    val media: Media)