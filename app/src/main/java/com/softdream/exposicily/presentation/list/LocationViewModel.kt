package com.softdream.exposicily.presentation.list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softdream.exposicily.BuildConfig
import com.softdream.exposicily.data.remote.DtoLocation
import com.softdream.exposicily.data.remote.LocationApiService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationViewModel : ViewModel() {
    private var restInterface: LocationApiService
    var state = mutableStateOf(emptyList<DtoLocation>())
    private val errorHandle =
        CoroutineExceptionHandler { _, exception ->
            run {
                Log.w(this.javaClass.simpleName, "API ERROR")
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
            val locations = getRemoteLocations()
            state.value = locations
        }
    }

    private suspend fun getRemoteLocations(): List<DtoLocation> {
        //Note Retrofit  set behind the scenes Dispatchers.IO for all suspend methods from within its interface
        return restInterface.getLocations()
    }

}