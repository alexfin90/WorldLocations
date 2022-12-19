package com.softdream.worldlocations.data

import com.softdream.worldlocations.data.remote.RemoteFlagsProperty
import com.softdream.worldlocations.data.remote.RemoteLocation
import com.softdream.worldlocations.data.remote.RemoteNameProperty
import com.softdream.worldlocations.domain.FlagsProperty
import com.softdream.worldlocations.domain.Location
import com.softdream.worldlocations.domain.NameProperty


object MockContent {

    fun getDomainLocation() = arrayListOf(
        Location(
            "IT", "Europe", "Sud Europe", NameProperty("Italia", "Repubblica Italiana"),
            FlagsProperty("", ""), "", 600000, 5555.4, 32.4, 34.4
        ),
        Location(
            "SPA", "Europe", "Sud Europe", NameProperty("Spagna", "Repubblica Italiana"),
            FlagsProperty("", ""), "", 500000, 5555.4, 32.4, 34.4
        ),
        Location(
            "UK", "Europe", "Nord Europe", NameProperty("Regno Unito", "UK"),
            FlagsProperty("", ""), "", 300000, 5555.4, 32.4, 34.4
        ),
    )

    fun getRemoteLocations() = getDomainLocation().map {
        RemoteLocation(
            it.id,
            RemoteNameProperty(it.nameProperty.common, it.nameProperty.official),
            it.region,
            it.subregion,
            it.flag,
            it.population,
            it.area,
            RemoteFlagsProperty(it.flagsProperty.pngURL, it.flagsProperty.svgURL),
            listOf(it.lat, it.lng) as List<Double>
        )
    }
}