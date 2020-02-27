package com.example.thecolorado.repository

import androidx.lifecycle.LiveData
import com.example.thecolorado.data.FlickrImage
import com.example.thecolorado.data.LatestImages
import com.example.thecolorado.network.FlickrAPI
import com.example.thecolorado.room.FlickrDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlickrImageRepository(private val database: FlickrDatabase) {

    val imageList: LiveData<List<FlickrImage>> = database.imagesDao.getImages()

    suspend fun refreshImageData(tag: String, nojsoncallback: Int, format: String)
    {
        withContext(Dispatchers.IO){
            val images = FlickrAPI.retrofitService.getPhotos(tag, nojsoncallback, format).await()
            database.imagesDao.insertAll(images.items)
        }
    }

}