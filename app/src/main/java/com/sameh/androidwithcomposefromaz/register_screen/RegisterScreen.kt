package com.sameh.androidwithcomposefromaz.register_screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sameh.androidwithcomposefromaz.R
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val state by viewModel.registerState.collectAsState()
    val interAction: RegisterInterAction = viewModel
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.registerEffect.collectLatest { effect ->
            when (effect) {
                is RegisterEffect.LoginNavigation -> {}

                is RegisterEffect.ShowToast -> {
                    Toast.makeText(context, effect.massage, Toast.LENGTH_SHORT).show()
                }

                RegisterEffect.LoginNavigationBack -> {}
            }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = ""
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create Account",
                modifier = Modifier.size(
                    width = 248.dp,
                    height = 45.dp
                ),
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Create an account so you can explore all the existing jobs",
                color = Color.Black,
                modifier = Modifier.size(
                    width = 326.dp,
                    height = 43.dp
                ),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(26.dp))
            OutlinedTextField(
                value = state.email,
                onValueChange = { interAction.onEmailChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text("email") },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    containerColor = colorResource(id = R.color.black)
                ),
                textStyle = TextStyle(
                    color = Color.White
                ),
            )
            if (state.isEmailValid().not() && state.email.isNotEmpty()) {
                Text(
                    text = "error in email",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp),
                    textAlign = TextAlign.Start
                )
            }
            Spacer(modifier = Modifier.height(35.dp))
            OutlinedTextField(
                value = state.password,
                onValueChange = { interAction.onPasswordChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                placeholder = { Text("Password") },
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (state.isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                textStyle = TextStyle(
                    color = Color.White
                ),
                trailingIcon = {
                    IconButton(onClick = { interAction.onClickVisiblePassword() }) {
                        Icon(
                            imageVector = if (state.isPasswordVisible) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            }, contentDescription = ""
                        )
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    containerColor = colorResource(id = R.color.black)
                )
            )
            if (state.isPasswordValid().not() && state.password.isNotEmpty()) {
                Text(
                    text = "wrong password",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp),
                    textAlign = TextAlign.Start
                )
            }
            Spacer(modifier = Modifier.height(35.dp))
            OutlinedTextField(
                value = state.passwordConfirm,
                onValueChange = { interAction.onConfirmChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                placeholder = { Text("Confirm password") },
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (state.isConfirmPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(onClick = { interAction.onClickVisibleConfirmPassword() }) {
                        Icon(
                            imageVector = if (state.isConfirmPasswordVisible) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            }, contentDescription = ""
                        )
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    containerColor = colorResource(id = R.color.black)

                ),
                textStyle = TextStyle(
                    color = Color.White
                ),
            )
            if (state.isPasswordConfirmValid().not() && state.passwordConfirm.isNotEmpty()) {
                Text(
                    text = "miss match in password",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp),
                    textAlign = TextAlign.Start
                )
            }
            Spacer(modifier = Modifier.height(45.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .height(60.dp),
                onClick = { interAction.onClickSignUp() },
                enabled = state.enableSignUpButton(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.black)
                ),
            ) {
                Text(text = "Sign in", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = "Already have an account",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    interAction.onClickHaveAccount()
                }
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Or continue with",
                fontSize = 15.sp,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.size(width = 200.dp, height = 44.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(modifier = Modifier.width(60.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    ),
                    onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
                Button(modifier = Modifier.width(60.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    ),
                    onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
                Button(modifier = Modifier.width(60.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    ),
                    onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}