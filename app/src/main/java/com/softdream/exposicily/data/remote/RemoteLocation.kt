package com.softdream.exposicily.data.remote


import com.google.gson.annotations.SerializedName
import com.softdream.exposicily.data.local.LocalLocation
import com.softdream.exposicily.data.local.LocalLocationProperty

data class RemoteLocation(
    @SerializedName("r_id") val id: Int,
    @SerializedName("properties")
    val property: RemoteLocationProperty
)

fun RemoteLocation.toLocalLocation() = LocalLocation(
    id = id,
    property = LocalLocationProperty(property.site,property.location,property.shortDescription,property.starsCounter,property.imageUrl)
)