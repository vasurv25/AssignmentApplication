package com.example.assignmentapplication.useCase

import com.example.assignmentapplication.model.LoginResult

class LoginUseCase(
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): LoginResult {

        val emailError = if (email.isBlank()) "Username cannot be blank" else null
        val passwordError = if (password.isBlank()) "Password cannot be blank" else null

        if (email != null && email.isNotBlank()
            && password != null && password.isNotBlank()) {
            return LoginResult(
                email, password, true
            )
        }
        return LoginResult(
            emailError, passwordError, false
        )
    }
}