package com.softdream.exposicily.presentation.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softdream.exposicily.BuildConfig
import com.softdream.exposicily.ExpoSicilyApplication
import com.softdream.exposicily.R
import com.softdream.exposicily.data.local.LocalLocation
import com.softdream.exposicily.data.local.LocationsDb
import com.softdream.exposicily.data.remote.LocationApiService
import com.softdream.exposicily.data.remote.toLocalLocation
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.UnknownHostException

class LocationDetailViewModel(stateHandle: SavedStateHandle) : ViewModel() {
    private var restInterface: LocationApiService
    private var locationDao = LocationsDb.getDaoInstance(ExpoSicilyApplication.getAppContext())

    var state = mutableStateOf((LocationDetailScreenState()))

    private val errorHandle =
        CoroutineExceptionHandler { _, exception ->
            run {
                Log.w(this.javaClass.simpleName, "API ERROR")
                state.value =
                    state.value.copy(isLoading = false, error = exception.message ?: "API ERROR")
                exception.printStackTrace()
            }
        }


    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create()
        ).baseUrl(BuildConfig.LOCATIONS_BASE_URL).build()
        restInterface = retrofit.create(LocationApiService::class.java)
        val lastIDLocation = stateHandle.get<Int>("location_id") ?: 0
        state.value = state.value.copy(lastIDLocation = lastIDLocation)
        getLocation(lastIDLocation)
    }

    private fun getLocation(id: Int) {

        //Note launch use for default  Dispatchers.MAIN
        viewModelScope.launch(errorHandle) {
            val location = getLocationByID(id)
            state.value = state.value.copy(location = location, isLoading = false)
        }
    }


    private suspend fun getLocationByID(id: Int): LocalLocation? {
        return withContext(Dispatchers.IO) {
            try {
                refreshCache(id)
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        if (locationDao!!.getLocationByID(id) == null) {
                            state.value = state.value.copy(
                                isLoading = false,
                                error =
                                ExpoSicilyApplication.getAppContext()
                                    .getString(R.string.network_error)
                            )
                        }
                    }
                    else -> throw  e
                }
            }
            return@withContext locationDao?.getLocationByID(id)
        }
    }

    private suspend fun refreshCache(id: Int) {
        val responseMap = restInterface.getLocation(id)
        locationDao?.addLocation(responseMap.values.first().toLocalLocation())
    }

    fun retryGetLocation() {
        state.value = state.value.copy(isLoading = true, error = "")
        getLocation(state.value.lastIDLocation)
    }
}