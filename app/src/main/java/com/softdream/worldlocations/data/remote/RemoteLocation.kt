package com.softdream.worldlocations.data.remote


import com.google.gson.annotations.SerializedName
import com.softdream.worldlocations.data.local.LocalFlagsProperty
import com.softdream.worldlocations.data.local.LocalLocation
import com.softdream.worldlocations.data.local.LocalNameProperty

data class RemoteLocation(
    @SerializedName("cca2")
    val id: String,
    @SerializedName("name")
    val name: RemoteNameProperty,
    @SerializedName("region")
    val region: String?,
    @SerializedName("subregion")
    val subregion: String?,
    @SerializedName("flag")
    val flag: String?,
    @SerializedName("population")
    val population: Long?,
    @SerializedName("area")
    val area: Double?,
    @SerializedName("flags")
    val flags: RemoteFlagsProperty,
    @SerializedName("latlng")
    val latlng: List<Double>,
    @SerializedName("capital")
    val capital: List<String>?
)

fun RemoteLocation.toLocalLocation() = LocalLocation(
    id = id,
    region = region,
    subregion = subregion,
    population = population,
    flag = flag,
    area = area,
    lat = latlng[0],
    lng = latlng[1],
    capital = capital?.firstOrNull() ?: "",
    nameProperty = LocalNameProperty(name.common, name.official),
    flagsProperty = LocalFlagsProperty(flags.pngURL, flags.svgURL)
)