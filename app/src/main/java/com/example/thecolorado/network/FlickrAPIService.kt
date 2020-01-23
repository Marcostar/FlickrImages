package com.example.thecolorado.network

import com.example.thecolorado.data.LatestImages
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrAPIService {

    @GET("photos_public.gne")
    fun getPhotos(
        @Query("tags") tag: String,
        @Query("nojsoncallback") nojsoncallback: Int,
        @Query("format") format: String
    ): Deferred<LatestImages>

}