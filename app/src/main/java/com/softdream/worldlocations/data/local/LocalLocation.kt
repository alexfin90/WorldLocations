package com.softdream.worldlocations.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.softdream.worldlocations.domain.FlagsProperty
import com.softdream.worldlocations.domain.Location
import com.softdream.worldlocations.domain.NameProperty


@Entity(tableName = "locations")
data class LocalLocation(
    @ColumnInfo(name = "cca2")
    @PrimaryKey val id: String,
    @ColumnInfo(name = "region")
    val region: String?,
    @ColumnInfo(name = "subregion")
    val subregion: String?,
    @ColumnInfo(name = "flag")
    val flag: String?,
    @ColumnInfo(name = "population")
    val population: Long?,
    @ColumnInfo(name = "area")
    val area: Double?,

    @ColumnInfo(name = "lat")
    val lat: Double?,
    @ColumnInfo(name = "long")
    val lng: Double?,

    @ColumnInfo(name = "capital")
    val capital : String,


    @Embedded val nameProperty: LocalNameProperty,
    @Embedded val flagsProperty: LocalFlagsProperty
)

fun LocalLocation.toLocation() = Location(
    id = id,
    region = region,
    subregion = subregion,
    population = population,
    area = area,
    flag = flag,
    lat = lat,
    lng = lng,
    capital = capital,
    nameProperty = NameProperty(nameProperty.common, nameProperty.official),
    flagsProperty = FlagsProperty(flagsProperty.pngURL, flagsProperty.svgURL)
)