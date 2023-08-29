package com.lexmerciful.to_dolist.presentation.sign_up

import android.util.Patterns

data class SignUpValidationResult(
    val isEmailValid: Boolean,
    val isPasswordValid: Boolean,
    val isConfirmPasswordValid: Boolean
)

fun validateSignUpFields(
    email: String,
    password: String,
    confirmPassword: String
): SignUpValidationResult {
    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordValid = password.length >= 6 // You can add more password criteria
    val isConfirmPasswordValid = confirmPassword == password

    return SignUpValidationResult(
        isEmailValid = isEmailValid,
        isPasswordValid = isPasswordValid,
        isConfirmPasswordValid = isConfirmPasswordValid
    )
}
