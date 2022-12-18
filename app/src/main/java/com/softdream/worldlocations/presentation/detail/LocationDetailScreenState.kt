package com.softdream.worldlocations.presentation.detail

import com.softdream.worldlocations.domain.Location

data class LocationDetailScreenState(
    val location: Location? = null,
    val isLoading: Boolean = true,
    val error: String = "",
    val lastIDLocation: String = ""
)
