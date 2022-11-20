package com.softdream.exposicily.data

import com.softdream.exposicily.BuildConfig
import com.softdream.exposicily.ExpoSicilyApplication
import com.softdream.exposicily.R
import com.softdream.exposicily.data.local.LocalLocation
import com.softdream.exposicily.data.local.LocationDao
import com.softdream.exposicily.data.local.LocationsDb
import com.softdream.exposicily.data.remote.LocationApiService
import com.softdream.exposicily.data.remote.toLocalLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.UnknownHostException

class LocationRepository {
    private var restInterface: LocationApiService
    private var locationDao: LocationDao?

    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create()
        ).baseUrl(BuildConfig.LOCATIONS_BASE_URL).build()
        restInterface = retrofit.create(LocationApiService::class.java)
        locationDao = LocationsDb.getDaoInstance(ExpoSicilyApplication.getAppContext())
    }

    suspend fun getAllLocations(): List<LocalLocation> {

        return withContext(Dispatchers.IO) {
            try {
                refreshCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        if (locationDao!!.getAll().isEmpty())
                            throw Exception(
                                ExpoSicilyApplication.getAppContext()
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
        //Note Retrofit  set behind the scenes Dispatchers.IO for all suspend methods from within its interface
        val locations = restInterface.getLocations()
        locationDao?.addAll(locations.map { it.toLocalLocation() })
    }

    suspend fun getLocationByID(id: Int): LocalLocation? {
        return withContext(Dispatchers.IO) {
            try {
                refreshCache(id)
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        if (locationDao!!.getLocationByID(id) == null) {
                            throw Exception(
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
}