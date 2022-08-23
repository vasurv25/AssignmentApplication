package com.example.assignmentapplication

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.example.assignmentapplication.common.TextFieldState
import com.example.assignmentapplication.presentation.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val emailState = loginViewModel.emailState.value
        val passwordState = loginViewModel.passwordState.value
        loginViewModel.loginRequestLiveData.observe(this, Observer {
                 if (it) {
                Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Log In Failed", Toast.LENGTH_SHORT).show()
            }
        })
        setContent {
            LoginUI(loginViewModel, emailState, passwordState)
        }
    }
}

@Composable
fun LoginUI(
    loginViewModel: LoginViewModel,
    emailState: TextFieldState,
    passwordState: TextFieldState
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Login here",
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Left,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it
                            loginViewModel.setEmail(email)},
            label = { Text("Enter your email") },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "Person")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 10.dp),
            isError = emailState.error != null
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it
                loginViewModel.setPassword(it) },
            label = { Text("Enter your password") },
            leadingIcon = {
                Icon(Icons.Default.Info, contentDescription = "Password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 10.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = passwordState.error != null
        )

        OutlinedButton(
            onClick = { loginViewModel.loginUser() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 10.dp)
        ) {
            Text(
                text = "Login",
                textAlign = TextAlign.Center
            )
        }
    }
}

fun logged(email: String, password: String, context: Context) {
    Toast.makeText(context, "Logged In Successfully", Toast.LENGTH_SHORT).show()
}
