package com.softdream.worldlocations.domain

import com.softdream.worldlocations.data.LocationRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor( private val repository: LocationRepository) {
    //contain only one method invoke for business logic
    suspend operator fun invoke(): List<Location> {
        return repository.getAllLocations().sortedByDescending { it.property.starsCounter }
    }
}