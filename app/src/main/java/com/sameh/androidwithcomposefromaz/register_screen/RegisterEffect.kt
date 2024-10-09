package com.sameh.androidwithcomposefromaz.register_screen

sealed class RegisterEffect {
    data class LoginNavigation(val route:String): RegisterEffect()
    data object LoginNavigationBack: RegisterEffect()
    data class ShowToast(val massage : String ): RegisterEffect()
}