package com.lexmerciful.to_dolist.presentation.sign_in

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)