package com.softdream.exposicily


import com.softdream.exposicily.data.remote.RemoteLocation
import com.softdream.exposicily.data.remote.RemoteLocationProperty
import com.softdream.exposicily.domain.Location
import com.softdream.exposicily.domain.LocationProperty

object DummyContent {

    fun getDomainLocation() = arrayListOf(
      Location(0, LocationProperty("Teatro Bellini","Catania","Norma",3,"")),
        Location(1, LocationProperty("Teatro Bellini","Catania","Norma",3,"")),
                Location(2, LocationProperty("Teatro Bellini","Catania","Norma",3,""))
    )
    fun getRemoteLocations() = getDomainLocation().map {
        RemoteLocation(it.id, RemoteLocationProperty(it.property.site,it.property.location,it.property.shortDescription,it.property.starsCounter,it.property.imageUrl))
    }
}