package com.softdream.exposicily.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {

    @Query("SELECT * FROM locations")
    suspend fun getAll(): List<LocalLocation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(locations: List<LocalLocation>)
}