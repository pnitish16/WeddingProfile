package com.demoapp.weddingprofile.api

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Module
class RetrofitApi @Inject constructor() {

    private val BASE_URL = "https://randomuser.me/api/";

    @Singleton
    @Provides
    fun getProfileApi(): ProfileApi {
        val retrofitInstance = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build();
        return retrofitInstance.create(ProfileApi::class.java);
    }

}