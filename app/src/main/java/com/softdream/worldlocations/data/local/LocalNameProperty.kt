package com.softdream.worldlocations.data.local

import androidx.room.ColumnInfo

data class LocalNameProperty(
    @ColumnInfo(name = "common") val common: String,
    @ColumnInfo(name = "official") val official: String
)
