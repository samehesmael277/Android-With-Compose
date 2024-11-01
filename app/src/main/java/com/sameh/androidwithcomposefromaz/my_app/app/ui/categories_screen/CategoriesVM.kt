package com.sameh.androidwithcomposefromaz.my_app.app.ui.categories_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameh.androidwithcomposefromaz.my_app.domain.usecase.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesVM @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<CategoriesState> = MutableStateFlow(CategoriesState())
    val state: StateFlow<CategoriesState> = _state

    init {
        getCategories()
    }

    private fun getCategories() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val categories = getCategoriesUseCase()
                _state.update {
                    it.copy(
                        isLoading = false,
                        categories = categories
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
                println(e)
            }
        }
    }
}