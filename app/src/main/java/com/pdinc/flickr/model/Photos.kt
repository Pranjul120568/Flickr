package com.pdinc.flickr.model

import com.google.gson.annotations.SerializedName
import com.pdinc.flickr.model.PhotoItem

data class Photos(

    @field:SerializedName("perpage")
    val perpage: Int? = null,

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("pages")
    val pages: Int? = null,

    @field:SerializedName("photo")
    val photo: List<PhotoItem?>? = null,

    @field:SerializedName("page")
    val page: Int? = null
)
