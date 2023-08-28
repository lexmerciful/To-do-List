package com.lexmerciful.to_dolist.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lexmerciful.to_dolist.data.DataStoreRepository
import com.lexmerciful.to_dolist.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.Onboard.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            dataStoreRepository.readOnBoardingState().collect { completed ->
                if (completed) {
                    _startDestination.value = Screen.SignUp.route
                } else {
                    _startDestination.value = Screen.Onboard.route
                }
            }
            _isLoading.value = false
        }
    }

}