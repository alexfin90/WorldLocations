package com.softdream.worldlocations.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface LocationApiService {
    @GET("/v3.1/all")
    suspend fun getLocations(): List<RemoteLocation>

    @GET("/v3.1/alpha/{code}")
    suspend fun getLocationByCode(@Path("code") code: String): List<RemoteLocation>

}