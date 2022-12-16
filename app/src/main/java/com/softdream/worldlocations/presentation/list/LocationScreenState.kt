package com.softdream.worldlocations.presentation.list


import com.softdream.worldlocations.domain.Location

data class LocationScreenState(
    val locations: List<Location> = emptyList(),
    val isLoading: Boolean = true,
    var error: String = ""
)