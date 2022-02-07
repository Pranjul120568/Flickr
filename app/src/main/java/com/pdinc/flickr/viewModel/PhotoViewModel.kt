package com.pdinc.flickr.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pdinc.flickr.model.PhotoItem
import com.pdinc.flickr.network.PhotoDataSourceImpl
import kotlinx.coroutines.runBlocking

class PhotoViewModel : ViewModel() {
    var PhotoData= mutableListOf<PhotoItem?>()
    private val photoSource = PhotoDataSourceImpl()
    init {
        runBlocking {
            Log.d("HERE IS VIEW MODEL","HERE")
            PhotoData = photoSource.getPhotos(1).body()?.photos!!.photo!!.toMutableList()
            if(PhotoData.isNotEmpty()){
                Log.d("HERE IS VIEW MODEL",PhotoData[0]!!.urlS!!)
            }
        }
    }
}