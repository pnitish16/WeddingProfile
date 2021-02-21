package com.demoapp.weddingprofile.api

import com.demoapp.weddingprofile.model.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileApi {

    @GET("api")
    suspend fun getProfiles(@Query("results") resultCount : Int): ProfileResponse

}