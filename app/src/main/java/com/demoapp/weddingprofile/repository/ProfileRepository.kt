package com.demoapp.weddingprofile.repository

import com.demoapp.weddingprofile.api.ProfileApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProfileRepository {

    private val api = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProfileApi::class.java)

    suspend fun getProfiles(resultCount: Int) = try {
        val response = api.getProfiles(resultCount)
        ProfileResult.Content(
            response.results ?: listOf()
        )
    } catch (t: Throwable) {
        ProfileResult.Error(t)
    }

}