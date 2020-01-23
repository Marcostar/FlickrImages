package com.example.thecolorado.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecolorado.data.LatestImages
import com.example.thecolorado.network.FlickrAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception


enum class RestApiStatus { LOADING, ERROR, DONE }

class ColoradoPhotosViewModel : ViewModel() {

    private val _status = MutableLiveData<RestApiStatus>()

    val status: LiveData<RestApiStatus>
        get() = _status

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _getImageList = MutableLiveData<LatestImages>()

    val getImageList: LiveData<LatestImages>
        get() = _getImageList


    init {
        downloadImageList("coloradomountains", 1, "json")
    }

    private fun downloadImageList(tag: String, nojsoncallback: Int, format: String) {
        uiScope.launch {
            val getImageDeferredList =
                FlickrAPI.retrofitService.getPhotos(tag, nojsoncallback, format)
            try {
                _status.value = RestApiStatus.LOADING
                val listresult = getImageDeferredList.await()
                _status.value = RestApiStatus.DONE
                _getImageList.value = listresult
            } catch (e: Exception) {
                _status.value = RestApiStatus.ERROR
                _getImageList.value = null
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
