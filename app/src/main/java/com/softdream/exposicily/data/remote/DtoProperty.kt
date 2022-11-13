package com.softdream.exposicily.data.remote

import com.google.gson.annotations.SerializedName

data class DtoProperty(
    @SerializedName("site") val site: String,
    @SerializedName("location") val location : String,
    @SerializedName("short_description") val shortDescription: String,
    @SerializedName("stars_counter") val starsCounter: Int,
    @SerializedName("image_url") val imageUrl: String)

