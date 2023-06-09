package com.softdream.worldlocations.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalLocation::class],
    version = 2,
    exportSchema = false
)
abstract class LocationsDb : RoomDatabase() {
    abstract val dao: LocationDao

}