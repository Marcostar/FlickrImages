package com.example.thecolorado.screens

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thecolorado.network.FlickrAPI
import com.example.thecolorado.repository.FlickrImageRepository
import com.example.thecolorado.room.getDatabase
import kotlinx.coroutines.*
import java.lang.Exception


enum class RestApiStatus { LOADING, ERROR, DONE }

class ColoradoPhotosViewModel(application: Application) : ViewModel() {

    private val flickrImageRepository = FlickrImageRepository(getDatabase(application))


    val imageList = flickrImageRepository.imageList


    private val _status = MutableLiveData<RestApiStatus>()

    val status: LiveData<RestApiStatus>
        get() = _status

    private var viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    init {
        downloadImageList("coloradomountains", 1, "json")
    }
//
    private fun downloadImageList(tag: String, nojsoncallback: Int, format: String) {
        viewModelScope.launch {
//            try {
//                _status.value = RestApiStatus.LOADING
//                flickrImageRepository.refreshImageData(tag,nojsoncallback,format)
//                _status.value = RestApiStatus.DONE
//            } catch (e: Exception) {
//                if(imageList.value?.isEmpty()!!)
//                _status.value = RestApiStatus.ERROR
//            }
            flickrImageRepository.refreshImageData(tag,nojsoncallback,format)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


//    init {
//        downloadImageList("coloradomountains", 1, "json")
//    }
//
//    private fun downloadImageList(tag: String, nojsoncallback: Int, format: String) {
//        uiScope.launch {
//            val getImageDeferredList =
//                FlickrAPI.retrofitService.getPhotos(tag, nojsoncallback, format)
//            try {
//                _status.value = RestApiStatus.LOADING
//                val listresult = getImageDeferredList.await()
//                _status.value = RestApiStatus.DONE
//                _getImageList.value = listresult
//            } catch (e: Exception) {
//                _status.value = RestApiStatus.ERROR
//                _getImageList.value = null
//            }
//        }
//    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ColoradoPhotosViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ColoradoPhotosViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}
