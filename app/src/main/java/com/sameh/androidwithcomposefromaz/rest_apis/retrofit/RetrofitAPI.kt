package com.sameh.androidwithcomposefromaz.rest_apis.retrofit

import com.sameh.androidwithcomposefromaz.rest_apis.categories.Categories
import com.sameh.androidwithcomposefromaz.rest_apis.meals.Meals
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("categories.php")
    suspend fun getCategories(): Categories

    @GET("search.php")
    suspend fun searchMeals(
        @Query("s") query: String
    ): Meals
}