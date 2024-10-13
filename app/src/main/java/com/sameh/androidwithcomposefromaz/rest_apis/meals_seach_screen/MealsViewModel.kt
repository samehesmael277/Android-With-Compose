package com.sameh.androidwithcomposefromaz.rest_apis.meals_seach_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameh.androidwithcomposefromaz.rest_apis.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MealsViewModel : ViewModel(), MealsDelegate {

    private val _state: MutableStateFlow<MealsState> = MutableStateFlow(MealsState())
    val state: StateFlow<MealsState> = _state

    private val service = RetrofitInstance.getService()

    override fun onQueryChanged(query: String) {
        _state.update {
            it.copy(
                query = query
            )
        }
        if (query.isNotEmpty()) {
            searchMeals(query)
        } else {
            _state.update {
                it.copy(
                    meals = emptyList()
                )
            }
        }
    }

    private fun searchMeals(query: String) {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val meals = service.searchMeals(query)
                _state.update {
                    it.copy(
                        isLoading = false,
                        meals = meals.meals ?: emptyList()
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