package com.sameh.androidwithcomposefromaz.register_screen

data class RegisterState(
    val password: String = "",
    val email: String = "",
    val passwordConfirm: String = "",
    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false
)

fun RegisterState.isEmailValid(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun RegisterState.isPasswordValid(): Boolean {
    return password.length >= 8
}

fun RegisterState.isPasswordConfirmValid(): Boolean {
    return passwordConfirm == password
}

fun RegisterState.enableSignUpButton(): Boolean {
    return isEmailValid() && isPasswordValid() && isPasswordConfirmValid()
}