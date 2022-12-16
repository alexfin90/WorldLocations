package com.softdream.worldlocations.domain

data class LocationProperty(
    val site: String,
    val location: String,
    val shortDescription: String,
    val starsCounter: Int,
    val imageUrl: String
)