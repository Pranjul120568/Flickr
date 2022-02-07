package com.pdinc.flickr.network

import com.pdinc.flickr.network.END_POINT.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient  {
val retrofit_client=Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    val retrofit_service by lazy {
        retrofit_client.create(Service::class.java)
    }
}