package com.softdream.worldlocations.data.local

import androidx.room.ColumnInfo


data class LocalFlagsProperty(@ColumnInfo(name = "png") val pngURL: String,
                              @ColumnInfo(name = "svg") val svgURL : String)

