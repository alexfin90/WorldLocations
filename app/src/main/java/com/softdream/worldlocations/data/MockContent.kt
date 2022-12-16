package com.softdream.worldlocations.data


import com.softdream.worldlocations.data.remote.RemoteLocation
import com.softdream.worldlocations.data.remote.RemoteLocationProperty
import com.softdream.worldlocations.domain.Location
import com.softdream.worldlocations.domain.LocationProperty

object MockContent {

    fun getDomainLocation() = arrayListOf(
      Location(0, LocationProperty("Teatro Bellini","Catania","Topdsfsdf",10,"")),
        Location(1, LocationProperty("Teatro Greco","Taormina","sfdsfsdf",5,"")),
                Location(2, LocationProperty("Teatro Antico","Siracusa","dfdsfdsf",1,""))
    )
    fun getRemoteLocations() = getDomainLocation().map {
        RemoteLocation(it.id, RemoteLocationProperty(it.property.site,it.property.location,it.property.shortDescription,it.property.starsCounter,it.property.imageUrl))
    }
}