package com.softdream.exposicily.data.remote

import retrofit2.http.GET

interface LocationApiService {
    @GET("/unescosites.json")
    suspend fun getLocations() : List<DtoLocation>
}