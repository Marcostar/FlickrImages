package com.example.thecolorado.data

data class LatestImages(val items: List<FlickrImage>)

data class FlickrImage(val title: String, val media: Media)

data class Media(val m: String)