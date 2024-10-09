package com.sameh.androidwithcomposefromaz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.sameh.androidwithcomposefromaz.rest_apis.categories_screen.CategoriesScreen
import com.sameh.androidwithcomposefromaz.ui.theme.AndroidWithComposeFromAZTheme
import com.sameh.androidwithcomposefromaz.whats_app.WhatsAppScreen

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
