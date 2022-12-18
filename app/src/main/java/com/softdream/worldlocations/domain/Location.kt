package com.softdream.worldlocations.domain



data class Location(
    val id: String,
    val region: String?,
    val subregion: String?,
    val nameProperty: NameProperty,
    val flagsProperty: FlagsProperty,
    val flag: String?,
    val population: Long?,
    val area: Double?
)
