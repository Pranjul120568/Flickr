package com.pdinc.flickr

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.pdinc.flickr.adapter.PhotoAdapter
import com.pdinc.flickr.databinding.ActivityMainBinding
import com.pdinc.flickr.model.PhotoItem
import com.pdinc.flickr.network.PhotoDataSourceImpl
import com.pdinc.flickr.viewModel.DefaultViewModelFactory
import com.pdinc.flickr.viewModel.PhotoViewModel
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var photoViewModel: PhotoViewModel
    private val photoSource = PhotoDataSourceImpl()
    private val photoAdapter = PhotoAdapter()
    private var originalList = arrayListOf<PhotoItem?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        runBlocking {
            fetchPhotos()
        }
        photoViewModel=ViewModelProvider(this,DefaultViewModelFactory(this)).get(PhotoViewModel::class.java)
        photoAdapter.swapData(originalList)
        binding.imagesRv.apply {
            adapter = photoAdapter
            layoutManager =
                GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false)
        }
    }

    private suspend fun fetchPhotos() {
        val result = photoSource.getPhotos(1)
        if (result.isSuccessful) {
            result.body()!!.photos!!.photo?.let { originalList.addAll(it) }
            Log.d("Here is the urls", result.body()!!.photos!!.photo!![0]!!.urlS!!)
        } else {
            Toast.makeText(this@MainActivity, "Network Error!", Toast.LENGTH_SHORT).show()
        }
        if (originalList.isNotEmpty()) {
            Log.d("DATA IS", "INSERTED")
        }
    }
}