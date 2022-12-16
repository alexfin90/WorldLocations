package com.softdream.worldlocations.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.softdream.worldlocations.domain.Location
import com.softdream.worldlocations.domain.LocationProperty


@Entity(tableName = "locations")
data class LocalLocation(
    @ColumnInfo(name = "r_id")
    @PrimaryKey val id: Int,
    @Embedded val property: LocalLocationProperty
)

fun LocalLocation.toLocation() = Location(
    id = id,
    property = LocationProperty(property.site,property.location,property.shortDescription,property.starsCounter,property.imageUrl)
)