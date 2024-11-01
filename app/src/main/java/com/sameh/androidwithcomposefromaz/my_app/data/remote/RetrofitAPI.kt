package com.sameh.androidwithcomposefromaz.my_app.data.remote

import com.sameh.androidwithcomposefromaz.my_app.data.dto.category.Categories
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("categories.php")
    suspend fun getCategories(): Categories
}