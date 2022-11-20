package com.softdream.exposicily.presentation.detail

import com.softdream.exposicily.data.local.LocalLocation

data class LocationDetailScreenState(
    val location: LocalLocation? = null,
    val isLoading: Boolean = true,
    val error: String = "",
    val lastIDLocation: Int = 0
)
