package com.softdream.exposicily.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalLocation::class],
    version = 1,
    exportSchema = false
)
abstract class LocationsDb : RoomDatabase() {
    abstract val dao: LocationDao

}