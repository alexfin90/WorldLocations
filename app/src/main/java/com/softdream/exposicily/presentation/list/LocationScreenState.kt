package com.softdream.exposicily.presentation.list


import com.softdream.exposicily.domain.Location

data class LocationScreenState(
    val locations: List<Location> = emptyList(),
    val isLoading: Boolean = true,
    var error: String = ""
)