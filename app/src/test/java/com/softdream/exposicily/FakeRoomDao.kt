package com.softdream.exposicily

import com.softdream.exposicily.data.local.LocalLocation
import com.softdream.exposicily.data.local.LocationDao
import kotlinx.coroutines.delay

class FakeRoomDao : LocationDao {
    private var locations = HashMap<Int, LocalLocation>()

    override suspend fun getAll(): List<LocalLocation> {
        delay(500)
        return locations.values.toList()
    }

    override suspend fun addAll(locations: List<LocalLocation>) {
        locations.forEach { this.locations[it.id] = it }
    }

    override suspend fun getLocationByID(id: Int): LocalLocation? {
        return this.locations[id]
    }

    override suspend fun addLocation(locations: LocalLocation) {
        this.locations[locations.id] = locations
    }
}