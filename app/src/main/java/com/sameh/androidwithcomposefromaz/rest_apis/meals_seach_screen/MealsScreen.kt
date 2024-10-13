package com.sameh.androidwithcomposefromaz.rest_apis.meals_seach_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.sameh.androidwithcomposefromaz.rest_apis.meals.Meal

@Composable
fun MealsScreen(
    mealsVM: MealsViewModel = viewModel()
) {
    val state by mealsVM.state.collectAsState()

    MealsScreenContent(
        state = state,
        delegate = mealsVM
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealsScreenContent(
    state: MealsState,
    delegate: MealsDelegate
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.statusBars),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        stickyHeader {
            OutlinedTextField(
                value = state.query,
                onValueChange = {
                    delegate.onQueryChanged(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                placeholder = {
                    Text(text = "Search...")
                }
            )
        }

        if (state.isLoading) {
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        } else {
            if (state.query.isEmpty()) {
                item {
                    Text(text = "Search for a meal")
                }
            } else {
                if (state.meals.isEmpty()) {
                    item {
                        Text(text = "not results found")
                    }
                } else {
                    items(state.meals) {
                        MealItem(it)
                    }
                }
            }
        }
    }
}

@Composable
fun MealItem(
    meal: Meal
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = meal.strMealThumb,
            contentDescription = "meal image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = meal.mealName.orEmpty())
        Text(text = meal.strCategory.orEmpty())
    }
}