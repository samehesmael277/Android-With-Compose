package com.sameh.androidwithcomposefromaz.my_app.app.ui.categories_screen

import com.sameh.androidwithcomposefromaz.my_app.domain.model.Category


data class CategoriesState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
)
