package com.softdream.worldlocations.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {
    @GET("/v3.1/all")
    suspend fun getLocations(): List<RemoteLocation>

    @GET("/v3.1/alpha/{code}\"r_id\"")
    suspend fun getLocationByCode(@Query("equalTo") code: String): List<RemoteLocation>

    @GET("/unescosites.json?orderBy=\"r_id\"")
    suspend fun getLocation(@Query("equalTo") id: Int): Map<String,RemoteLocation>


}