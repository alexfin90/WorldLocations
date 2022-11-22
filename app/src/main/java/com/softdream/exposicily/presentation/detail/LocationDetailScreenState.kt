package com.softdream.exposicily.presentation.detail

import com.softdream.exposicily.domain.Location

data class LocationDetailScreenState(
    val location: Location? = null,
    val isLoading: Boolean = true,
    val error: String = "",
    val lastIDLocation: Int = 0
)
