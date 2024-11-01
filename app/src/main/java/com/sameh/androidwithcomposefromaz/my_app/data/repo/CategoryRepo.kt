package com.sameh.androidwithcomposefromaz.my_app.data.repo

import com.sameh.androidwithcomposefromaz.my_app.data.mapper.toDomain
import com.sameh.androidwithcomposefromaz.my_app.data.remote.RetrofitAPI
import com.sameh.androidwithcomposefromaz.my_app.domain.model.Category
import com.sameh.androidwithcomposefromaz.my_app.domain.repo.ICategoryRepo
import javax.inject.Inject

class CategoryRepo @Inject constructor(
    private val retrofitAPI: RetrofitAPI
): ICategoryRepo {

    override suspend fun getCategories(): List<Category> {
        return retrofitAPI.getCategories().toDomain()
    }
}