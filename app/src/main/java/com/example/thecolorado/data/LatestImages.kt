package com.example.thecolorado.data

import com.google.gson.annotations.SerializedName


data class LatestImages(@SerializedName("items")val items: List<FlickrImage>)
