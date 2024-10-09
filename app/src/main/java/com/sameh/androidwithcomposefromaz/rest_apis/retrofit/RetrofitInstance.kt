package com.sameh.androidwithcomposefromaz.rest_apis.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val service: RetrofitAPI = retrofit.create(RetrofitAPI::class.java)

    fun getService(): RetrofitAPI = service
}
