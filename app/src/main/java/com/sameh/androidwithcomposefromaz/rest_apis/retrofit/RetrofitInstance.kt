package com.sameh.androidwithcomposefromaz.rest_apis.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            // .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    private val service: RetrofitAPI = retrofit.create(RetrofitAPI::class.java)

    fun getService(): RetrofitAPI = service
}
