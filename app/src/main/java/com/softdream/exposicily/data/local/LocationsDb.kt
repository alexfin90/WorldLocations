package com.softdream.exposicily.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LocalLocation::class],
    version = 1,
    exportSchema = false
)
abstract class LocationsDb : RoomDatabase() {
    abstract val dao: LocationDao

    companion object {
        @Volatile
        private var INSTANCE: LocationDao? = null

        fun getDaoInstance ( context: Context ): LocationDao? {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = buildDatabase(context).dao
                    INSTANCE = instance
                }
                return INSTANCE
            }

        }
        private fun buildDatabase(context: Context): LocationsDb =
            Room.databaseBuilder(
                context.applicationContext,
                LocationsDb::class.java,
                "locations_database"
            ).fallbackToDestructiveMigration().build()
    }
}