package com.lexmerciful.to_dolist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lexmerciful.to_dolist.presentation.onboarding.OnboardScreen
import com.lexmerciful.to_dolist.presentation.sign_in.SignInScreen
import com.lexmerciful.to_dolist.presentation.sign_up.SignUpScreen
import com.lexmerciful.to_dolist.presentation.sign_up.SignUpState
import com.lexmerciful.to_dolist.presentation.sign_up.SignUpViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {

    val signUpViewModel = hiltViewModel<SignUpViewModel>()
    val state by signUpViewModel.signUpState.collectAsStateWithLifecycle(initialValue = SignUpState())

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Onboard.route) {
            OnboardScreen(navController = navController)
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(navController = navController, state = state, onSignUpClick = signUpViewModel::registerUser )
        }

        composable(Screen.SignIn.route) {
            SignInScreen(navController = navController)
        }
    }
}