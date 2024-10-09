package com.sameh.androidwithcomposefromaz.rest_apis.categories_screen

import com.sameh.androidwithcomposefromaz.rest_apis.categories.Category

data class CategoriesState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
)
