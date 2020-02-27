package com.example.thecolorado.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.thecolorado.Converters.MediaConverter
import com.example.thecolorado.data.FlickrImage


@Database(entities = [FlickrImage::class], version = 1, exportSchema = false)
@TypeConverters(MediaConverter::class)
abstract class FlickrDatabase: RoomDatabase() {
    abstract val imagesDao: ImagesDao
}


private lateinit var INSTANCE: FlickrDatabase


fun getDatabase(context: Context): FlickrDatabase{

    synchronized(FlickrDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context,
                FlickrDatabase::class.java,
                "flickerImages").build()
        }
    }
    return INSTANCE
}