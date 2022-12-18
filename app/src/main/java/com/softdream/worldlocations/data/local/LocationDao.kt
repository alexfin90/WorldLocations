package com.softdream.worldlocations.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {

    @Query("SELECT * FROM locations")
    suspend fun getAll(): List<LocalLocation>

    @Query("SELECT * FROM locations WHERE cca2=:id")
    suspend fun getLocationByID(id: String) : LocalLocation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(locations: List<LocalLocation>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLocation(locations: LocalLocation)

}