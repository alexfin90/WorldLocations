package com.softdream.exposicily.data

import android.content.Context
import com.softdream.exposicily.R
import com.softdream.exposicily.data.local.LocationDao
import com.softdream.exposicily.data.local.toLocation
import com.softdream.exposicily.data.remote.LocationApiService
import com.softdream.exposicily.data.remote.toLocalLocation
import com.softdream.exposicily.domain.Location
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepository @Inject constructor(
    @ApplicationContext private val application : Context,
    private var restInterface: LocationApiService,
    private var locationDao: LocationDao?
) {


    /* init {
         val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(
             GsonConverterFactory.create()
         ).baseUrl(BuildConfig.LOCATIONS_BASE_URL).build()
         restInterface = retrofit.create(LocationApiService::class.java)
         locationDao = LocationsDb.getDaoInstance(ExpoSicilyApplication.getAppContext())
     }*/

    suspend fun getAllLocations(): List<Location> {
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
                              application.getString(R.string.generic_error)
                            )
                    }
                    else -> throw  e
                }
            }
            return@withContext locationDao!!.getAll().map { it.toLocation() }
        }
    }

    private suspend fun refreshCache() {

        //Note Retrofit  set behind the scenes Dispatchers.IO for all suspend methods from within its interface
        val locations = restInterface.getLocations()
        locationDao?.addAll(locations.map { it.toLocalLocation() })
    }

    suspend fun getLocationByID(id: Int): Location? {
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

                            )
                        }
                    }
                    else -> throw  e
                }
            }
            return@withContext locationDao?.getLocationByID(id)?.toLocation()
        }
    }

    private suspend fun refreshCache(id: Int) {
        val responseMap = restInterface.getLocation(id)
        locationDao?.addLocation(responseMap.values.first().toLocalLocation())
    }
}