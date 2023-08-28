package com.lexmerciful.to_dolist.navigation

sealed class Screen(val route: String) {
    object Onboard: Screen("onboard")
    object SignUp: Screen("sign_up")
    object SignIn: Screen("sign_in")
    object Home: Screen("Home")
}
