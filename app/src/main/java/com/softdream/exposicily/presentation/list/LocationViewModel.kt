package com.softdream.exposicily.presentation.list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
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

class LocationViewModel : ViewModel() {
    private var restInterface: LocationApiService
    private var locationDao = LocationsDb.getDaoInstance(ExpoSicilyApplication.getAppContext())
    val state = mutableStateOf(LocationScreenState(listOf()))

    private val errorHandle =
        CoroutineExceptionHandler { _, exception ->
            run {
                Log.w(this.javaClass.simpleName, "API ERROR")
                state.value = state.value.copy(isLoading = false, error = exception.message ?: "API ERROR")
                exception.printStackTrace()
            }
        }

    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create()
        ).baseUrl(BuildConfig.LOCATIONS_BASE_URL).build()
        restInterface = retrofit.create(LocationApiService::class.java)
        getLocations()
    }

    private fun getLocations() {
        //Note launch use for default  Dispatchers.MAIN
        viewModelScope.launch(errorHandle) {
            val locations = getAllLocations()
            state.value = state.value.copy(locations = locations, isLoading = false )
        }
    }

    fun retryGetLocations() {
        state.value = state.value.copy(isLoading = true, error = "")
        getLocations()
    }


    private suspend fun getAllLocations(): List<LocalLocation> {
        //Note Retrofit  set behind the scenes Dispatchers.IO for all suspend methods from within its interface

        return withContext(Dispatchers.IO) {
            try {
                refreshCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        if (locationDao!!.getAll().isEmpty())
                            state.value = state.value.copy(
                                isLoading = false,
                                error = ExpoSicilyApplication.getAppContext()
                                    .getString(R.string.network_error)
                            )
                    }
                    else -> throw  e
                }
            }
            return@withContext locationDao!!.getAll()
        }
    }

    private suspend fun refreshCache() {
        val locations = restInterface.getLocations()
        locationDao?.addAll(locations.map { it.toLocalLocation() })
    }


}