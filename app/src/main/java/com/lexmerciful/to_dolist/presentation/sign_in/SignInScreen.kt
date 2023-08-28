package com.lexmerciful.to_dolist.presentation.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lexmerciful.to_dolist.R
import com.lexmerciful.to_dolist.navigation.Screen
import com.lexmerciful.to_dolist.presentation.sign_up.SignInWithOAuthButton
import com.lexmerciful.to_dolist.presentation.sign_up.TextFieldEntries

@Composable
fun SignInScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            SignInTop()

            TextFieldEntries(email, stringResource(R.string.email), painterResource(id = R.drawable.email_icon), false) {
                email = it
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextFieldEntries(
                password,
                stringResource(R.string.password),
                painterResource(id = R.drawable.password_icon),
                true
            ) {
                password = it
            }

            Spacer(modifier = Modifier.height(16.dp))

            SignInButton {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }

            Spacer(modifier = Modifier.height(16.dp))

            ForgotPasswordText()
        }

        Column(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            SignInWithOAuthButton(
                modifier = Modifier,
                text = stringResource(R.string.sign_in_with_apple),
                icon = painterResource(id = R.drawable.apple_logo_black),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
            ) {

            }

            Spacer(modifier = Modifier.height(16.dp))

            SignInWithOAuthButton(
                modifier = Modifier,
                text = stringResource(R.string.sign_in_with_google),
                icon = painterResource(id = R.drawable.google_logo),
                colorFilter = null
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(color = MaterialTheme.colorScheme.surface)
                    ) {
                        append("Don't have an account? ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append("Sign Up")
                    }
                },
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(500),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.clickable {
                    navController.popBackStack()
                    navController.navigate(Screen.SignUp.route)
                }
            )

        }
    }
}

@Composable
fun SignInTop() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            painter = painterResource(id = R.drawable.welcome_logo),
            contentDescription = stringResource(R.string.logo),
            modifier = Modifier
                .padding(top = 16.dp)
                .width(76.dp)
                .height(40.dp)
        )

        Text(
            text = "Welcome back!",
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(300),
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.surface,
                textAlign = TextAlign.Center,
                letterSpacing = 3.44.sp,
            ),
            modifier = Modifier
                .padding(top = 40.dp)
                .height(10.dp)
                .fillMaxWidth()
        )

        Image(
            painter = painterResource(id = if (isSystemInDarkTheme()){
                R.drawable.signin_header_white
            } else {
                R.drawable.signin_header_black
            } ),
            contentDescription = stringResource(R.string.sign_in),
            modifier = Modifier
                .padding(top = 35.dp)
                .padding(bottom = 35.dp)
                .align(Alignment.Start)
        )

    }
}

@Composable
fun SignInButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(20),
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.sign_in),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ForgotPasswordText() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(color = MaterialTheme.colorScheme.surface)
                ) {
                    append("Forgot Password? ")
                }
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    append("Click here")
                }
            },
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                color = Color.Black,
                textAlign = TextAlign.Center,
            )
        )
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    SignInScreen(rememberNavController())
}


