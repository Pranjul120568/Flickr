package com.pdinc.flickr.model

import com.google.gson.annotations.SerializedName

data class PhotoItem(

    @field:SerializedName("owner")
    val owner: String? = null,

    @field:SerializedName("server")
    val server: String? = null,

    @field:SerializedName("height_s")
    val heightS: Int? = null,

    @field:SerializedName("width_s")
    val widthS: Int? = null,

    @field:SerializedName("url_s")
    val urlS: String? = null,

    @field:SerializedName("ispublic")
    val ispublic: Int? = null,

    @field:SerializedName("isfriend")
    val isfriend: Int? = null,

    @field:SerializedName("farm")
    val farm: Int? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("secret")
    val secret: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("isfamily")
    val isfamily: Int? = null
)

