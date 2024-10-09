package com.sameh.androidwithcomposefromaz.rest_apis.retrofit

import com.sameh.androidwithcomposefromaz.rest_apis.categories.Categories
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("categories.php")
    suspend fun getCategories(): Categories
}