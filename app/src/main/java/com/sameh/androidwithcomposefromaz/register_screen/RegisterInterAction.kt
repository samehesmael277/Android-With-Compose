package com.sameh.androidwithcomposefromaz.register_screen

interface RegisterInterAction {
    fun onClickSignUp()
    fun onClickHaveAccount()
    fun onConfirmChange (passwordConfirm: String)
    fun onEmailChange(email:String)
    fun onPasswordChange(password:String)
    fun onClickVisiblePassword()
    fun onClickVisibleConfirmPassword()
}