package com.softdream.worldlocations.data.remote

import com.google.gson.annotations.SerializedName

data class RemoteNameProperty(
    @SerializedName("common") val common: String,
    @SerializedName("official") val official : String)

