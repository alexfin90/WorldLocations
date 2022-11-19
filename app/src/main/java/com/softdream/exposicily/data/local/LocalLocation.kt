package com.softdream.exposicily.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "locations")
data class LocalLocation(
    @ColumnInfo(name = "r_id")
    @PrimaryKey val id: Int,
    @Embedded val property: LocalLocationProperty
)