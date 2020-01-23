package com.example.thecolorado.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object FlickrAPI {
    val retrofitService: FlickrAPIService by lazy { retrofit.create(FlickrAPIService::class.java) }
}


private const val BASE_URL =
    "https://api.flickr.com/services/feeds/"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()