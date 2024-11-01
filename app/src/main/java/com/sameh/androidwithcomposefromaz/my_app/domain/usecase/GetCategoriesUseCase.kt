package com.sameh.androidwithcomposefromaz.my_app.domain.usecase

import com.sameh.androidwithcomposefromaz.my_app.domain.repo.ICategoryRepo
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val mealsRepo: ICategoryRepo
) {

    suspend operator fun invoke() = mealsRepo.getCategories()
}
