package com.sameh.androidwithcomposefromaz.my_app.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sameh.androidwithcomposefromaz.my_app.app.ui.categories_screen.CategoriesScreen
import com.sameh.androidwithcomposefromaz.ui.theme.AndroidWithComposeFromAZTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidWithComposeFromAZTheme {
                CategoriesScreen()
            }
        }
    }
}
