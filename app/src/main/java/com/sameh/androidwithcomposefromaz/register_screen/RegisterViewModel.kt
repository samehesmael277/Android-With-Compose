package com.sameh.androidwithcomposefromaz.register_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel(), RegisterInterAction {

    private var _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    private var _registerEffect = MutableSharedFlow<RegisterEffect>()
    val registerEffect: SharedFlow<RegisterEffect> = _registerEffect.asSharedFlow()

    override fun onClickSignUp() {
        viewModelScope.launch {
            if (isCorrectEmail(_registerState.value.email) && isCorrectPassword(_registerState.value.password)) {
                _registerEffect.emit(RegisterEffect.ShowToast("register done"))
            } else {
                _registerEffect.emit(RegisterEffect.ShowToast("register failed"))
            }
        }
    }

    override fun onClickHaveAccount() {
        viewModelScope.launch {
            _registerEffect.emit(RegisterEffect.LoginNavigation("login"))
        }
    }

    override fun onEmailChange(email: String) {
        _registerState.update {
            it.copy(
                email = email
            )
        }
    }

    override fun onPasswordChange(password: String) {
        _registerState.update {
            it.copy(
                password = password
            )
        }
    }

    override fun onConfirmChange(passwordConfirm: String) {
        _registerState.update {
            it.copy(
                passwordConfirm = passwordConfirm
            )
        }
    }

    override fun onClickVisiblePassword() {
        _registerState.update {
            it.copy(
                isPasswordVisible = it.isPasswordVisible.not()
            )
        }
    }

    override fun onClickVisibleConfirmPassword() {
        _registerState.update {
            it.copy(
                isConfirmPasswordVisible = it.isConfirmPasswordVisible.not()
            )
        }
    }

    private fun isCorrectEmail(email: String): Boolean {
        return email == "sameh@gmail.com"
    }

    private fun isCorrectPassword(password: String): Boolean {
        return password == "12345678"
    }
}