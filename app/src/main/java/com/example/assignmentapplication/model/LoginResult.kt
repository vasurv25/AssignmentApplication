package com.example.assignmentapplication.model

data class LoginResult(
    val passwordError: String? = null,
    val emailError : String? = null,
    val isSuccess:Boolean = false
)