package com.pdinc.flickr

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.pdinc.flickr.adapter.PhotoAdapter
import com.pdinc.flickr.databinding.ActivityMainBinding
import com.pdinc.flickr.model.PhotoItem
import com.pdinc.flickr.model.PhotoResponse
import com.pdinc.flickr.network.PhotoDataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val photosource = PhotoDataSourceImpl()
    val photoAdapter = PhotoAdapter()
    private var originalList = arrayListOf<PhotoItem?>()
    lateinit var photoData: PhotoResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        runBlocking {
            fetchPhotos()
        }
        if (originalList.isEmpty()) {
            //Log.d("DATA IS","INSERTED")
        }
        photoAdapter.swapData(originalList)
        binding.imagesRv.apply {
            adapter = photoAdapter
            layoutManager =
                GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false)
        }
    }

    private suspend fun fetchPhotos() {
        val result = photosource.getPhotos(1)
        if (result.isSuccessful) {
            result.body()!!.photos!!.photo?.let { originalList.addAll(it) }
            Log.d("Here is the urls", result.body()!!.photos!!.photo!![0]!!.urlS!!)
        } else {
            Toast.makeText(this@MainActivity, "Network Eroor!", Toast.LENGTH_SHORT).show()
        }
        if (originalList.isNotEmpty()) {
            Log.d("DATA IS", "INSERTED")
        }
    }
}