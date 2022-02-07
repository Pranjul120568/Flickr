package com.pdinc.flickr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.pdinc.flickr.adapter.PhotoAdapter
import com.pdinc.flickr.databinding.ActivityMainBinding
import com.pdinc.flickr.model.PhotoItem
import com.pdinc.flickr.viewModel.DefaultViewModelFactory
import com.pdinc.flickr.viewModel.PhotoViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var photoViewModel: PhotoViewModel
    private val photoAdapter = PhotoAdapter()
    private var originalList = arrayListOf<PhotoItem?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        photoViewModel =
            ViewModelProvider(this, DefaultViewModelFactory(this)).get(PhotoViewModel::class.java)
        originalList.addAll(photoViewModel.PhotoData)
        photoAdapter.swapData(originalList)
        binding.imagesRv.apply {
            adapter = photoAdapter
            layoutManager =
                GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false)
        }
    }

}