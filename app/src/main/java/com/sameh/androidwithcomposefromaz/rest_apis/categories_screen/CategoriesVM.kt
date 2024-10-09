package com.sameh.androidwithcomposefromaz.rest_apis.categories_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameh.androidwithcomposefromaz.rest_apis.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesVM : ViewModel() {

    private val _state: MutableStateFlow<CategoriesState> = MutableStateFlow(CategoriesState())
    val state: StateFlow<CategoriesState> = _state

    private val service = RetrofitInstance.getService()

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
                val categories = service.getCategories()
                _state.update {
                    it.copy(
                        isLoading = false,
                        categories = categories.categories ?: emptyList()
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