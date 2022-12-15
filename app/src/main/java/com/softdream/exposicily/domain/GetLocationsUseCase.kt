package com.softdream.exposicily.domain

import com.softdream.exposicily.data.LocationRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor( private val repository: LocationRepository) {
    //contain only one method invoke for business logic
    suspend operator fun invoke(): List<Location> {
        return repository.getAllLocations().sortedBy { it.property.site }
    }
}