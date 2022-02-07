package com.pdinc.flickr.model

import com.google.gson.annotations.SerializedName

data class PhotoResponse(

	@field:SerializedName("stat")
	val stat: String? = null,

	@field:SerializedName("photos")
	val photos: Photos? = null
)

