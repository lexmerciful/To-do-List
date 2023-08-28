package com.lexmerciful.to_dolist.presentation.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lexmerciful.to_dolist.data.AuthRepository
import com.lexmerciful.to_dolist.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        authRepository.registerUser(email, password).collect{result ->
            when(result){
                is Resource.Error -> {
                    _signUpState.send(SignUpState(isError = result.message))
                }
                is Resource.Loading -> {
                    _signUpState.send(SignUpState(isLoading = true))
                }
                is Resource.Success -> {
                    _signUpState.send(SignUpState(isSuccess = "Sign Up Success"))
                }
            }
        }
    }

}