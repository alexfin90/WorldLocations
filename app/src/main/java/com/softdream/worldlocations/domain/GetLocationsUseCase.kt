package com.softdream.worldlocations.domain

import com.softdream.worldlocations.data.LocationRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor( private val repository: LocationRepository) {
    //contain only one method invoke for business logic
    //ordering for population
    suspend operator fun invoke(): List<Location> {
        return repository.getAllLocations().sortedByDescending { it.population}
    }
}