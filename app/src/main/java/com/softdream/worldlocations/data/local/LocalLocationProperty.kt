package com.softdream.worldlocations.data.local

import androidx.room.ColumnInfo


class LocalLocationProperty(
    @ColumnInfo(name = "site")
    val site: String,

    @ColumnInfo(name = "location")
    val location: String,

    @ColumnInfo(name = "short_description")
    val shortDescription: String,

    @ColumnInfo(name = "stars_counter")
    val starsCounter: Int,

    @ColumnInfo(name = "image_url")
    val imageUrl: String
)