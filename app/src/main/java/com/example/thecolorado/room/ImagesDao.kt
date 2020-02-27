package com.example.thecolorado.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.thecolorado.data.FlickrImage
import com.example.thecolorado.data.LatestImages

@Dao
interface ImagesDao {

    @Query("select * from FlickerImage")
    fun getImages(): LiveData<List<FlickrImage>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(images: List<FlickrImage>)

}