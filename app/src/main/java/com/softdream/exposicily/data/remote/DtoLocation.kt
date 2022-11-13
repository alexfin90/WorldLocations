package com.softdream.exposicily.data.remote

import com.google.gson.annotations.SerializedName

data class DtoLocation(
    @SerializedName("r_id") val id: Int,
    @SerializedName("properties")
    val property: DtoProperty
)
