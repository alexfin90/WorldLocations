package com.softdream.worldlocations.domain

import com.softdream.worldlocations.data.LocationRepository
import javax.inject.Inject

class GetLocationByIDUseCase @Inject constructor(private val repository: LocationRepository) {
    suspend operator fun invoke(id: String): Location? {
        return repository.getLocationByID(id)
    }
}