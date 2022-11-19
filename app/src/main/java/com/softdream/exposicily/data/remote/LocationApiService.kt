package com.softdream.exposicily.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {
    @GET("/unescosites.json")
    suspend fun getLocations(): List<RemoteLocation>

    @GET("/unescosites.json?orderBy=\"r_id\"")
    suspend fun getLocation(@Query("equalTo") id: Int): Map<String,RemoteLocation>
}