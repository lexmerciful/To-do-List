package com.lexmerciful.to_dolist.presentation.sign_up

data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)