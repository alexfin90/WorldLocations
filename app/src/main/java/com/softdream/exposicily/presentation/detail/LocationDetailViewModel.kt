package com.softdream.exposicily.presentation.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softdream.exposicily.BuildConfig
import com.softdream.exposicily.data.remote.LocationApiService
import com.softdream.exposicily.data.remote.RemoteLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationDetailViewModel(stateHandle: SavedStateHandle) : ViewModel() {
    private var restInterface: LocationApiService
    val state = mutableStateOf<RemoteLocation?>(null)

    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create()
        ).baseUrl(BuildConfig.LOCATIONS_BASE_URL).build()
        restInterface = retrofit.create(LocationApiService::class.java)
        val id = stateHandle.get<Int>("location_id") ?: 0
        viewModelScope.launch {
            state.value = getLocation(id)
        }
    }

    private suspend fun getLocation(id: Int): RemoteLocation {
        return withContext(Dispatchers.IO) {
            val responseMap = restInterface.getLocation(id)
            return@withContext responseMap.values.first()
        }
    }
}