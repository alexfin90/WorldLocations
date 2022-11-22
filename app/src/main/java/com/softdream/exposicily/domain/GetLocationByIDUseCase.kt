package com.softdream.exposicily.domain

import com.softdream.exposicily.data.LocationRepository

class GetLocationByIDUseCase() {
    private val repository = LocationRepository()
    suspend operator fun invoke(id: Int): Location? {
        return repository.getLocationByID(id)
    }
}