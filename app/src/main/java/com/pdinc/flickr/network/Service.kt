package com.pdinc.flickr.network

import android.util.Log
import com.pdinc.flickr.BuildConfig
import com.pdinc.flickr.model.PhotoResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.Exception

interface Service {
    companion object{
        const val response="services/rest/"
    }
    @GET(response)
    suspend fun getAllPhotos(
        @Query ("method")method:String="flickr.photos.getRecent",
        @Query ("per_page")totalImg:Int=20,
        @Query ("page")page:Int,
        @Query ("api_key") apiKey:String=BuildConfig.API_KEY,
        @Query ("format") format:String="json",
        @Query ("nojsoncallback") callback:Int=1,
        @Query ("extras") extras:String="url_s"
    ) :Response<PhotoResponse>
}
interface PhotoDataSource{
    suspend fun getPhotos(page:Int) :Response<PhotoResponse>
}
class PhotoDataSourceImpl : PhotoDataSource{
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private val retrofitClient: Service = RetrofitClient.retrofit_service
    override suspend fun getPhotos(page: Int): Response<PhotoResponse> =
        withContext(ioDispatcher){
            return@withContext try{
                val result=retrofitClient.getAllPhotos(page = 1)
                if(result.isSuccessful){
                    val allresponse=result.body()
                    Log.d("Query Ki maa ki", "Data Fetched")
                    Response.success(allresponse)
                }else {
                    Log.d("Query ki maa ki", "Data Not Fetched")
                    Response.success(null)
                }
            }catch (e:Exception){
                Log.d("Query ki maa ki", e.toString())
                Response.success(null)
            }
        }
}