package com.sameh.androidwithcomposefromaz.my_app.domain.repo

import com.sameh.androidwithcomposefromaz.my_app.domain.model.Category

interface ICategoryRepo {
    suspend fun getCategories(): List<Category>
}