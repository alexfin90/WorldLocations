package com.softdream.exposicily.domain

import com.softdream.exposicily.data.LocationRepository

class GetLocationsUseCase {
    //contain only one method invoke for business logic
    private val repository = LocationRepository()
    suspend operator fun invoke(): List<Location> {
        return repository.getAllLocations().sortedBy { it.property.site }
    }
}