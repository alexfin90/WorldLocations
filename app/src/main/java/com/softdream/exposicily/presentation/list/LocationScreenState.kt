package com.softdream.exposicily.presentation.list

import com.softdream.exposicily.data.local.LocalLocation

data class LocationScreenState(
    val locations: List<LocalLocation> = emptyList(),
    val isLoading: Boolean = true,
    var error: String = ""
)