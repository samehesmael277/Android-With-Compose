package com.sameh.androidwithcomposefromaz.rest_apis.meals_seach_screen

import com.sameh.androidwithcomposefromaz.rest_apis.meals.Meal

data class MealsState(
    val isLoading: Boolean = false,
    val meals: List<Meal> = emptyList(),
    val query: String = ""
)
