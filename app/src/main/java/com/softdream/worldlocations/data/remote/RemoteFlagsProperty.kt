package com.softdream.worldlocations.data.remote

import com.google.gson.annotations.SerializedName

data class RemoteFlagsProperty(@SerializedName("png") val pngURL: String,
                               @SerializedName("svg") val svgURL : String)
