package com.sameh.androidwithcomposefromaz.my_app.data.mapper

import com.sameh.androidwithcomposefromaz.my_app.data.dto.category.Categories
import com.sameh.androidwithcomposefromaz.my_app.domain.model.Category

fun Categories.toDomain(): List<Category> {
    return this.categories?.map {
        Category(
            id = it.idCategory.orEmpty(),
            name = it.strCategory.orEmpty()
        )
    } ?: emptyList()
}