package com.lexmerciful.to_dolist.presentation.sign_up

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lexmerciful.to_dolist.R
import com.lexmerciful.to_dolist.navigation.Screen
import com.lexmerciful.to_dolist.ui.theme.PrimaryColor
import com.lexmerciful.to_dolist.ui.theme.TextFieldBgColor
import com.lexmerciful.to_dolist.ui.theme.TextFieldBgColorDarkTheme
import com.lexmerciful.to_dolist.ui.theme.TextFieldLabelColor
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavController,
    state: SignUpState,
    onSignUpClick: (email: String, password: String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(key1 = state?.isError) {
        state?.isError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(key1 = state?.isSuccess) {
        scope.launch {
            if (state?.isSuccess?.isNotEmpty() == true){
                val success = state?.isSuccess
                Toast.makeText(
                    context,
                    success,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(16.dp)){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            SignUpTop()

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

            TextFieldEntries(
                confirmPassword,
                stringResource(R.string.confirm_password),
                painterResource(id = R.drawable.password_icon),
                true
            ) {
                confirmPassword = it
            }

            Spacer(modifier = Modifier.height(16.dp))

            SignUpButton(state = state) {
                onSignUpClick(email, password)
            }

            //Spacer(modifier = Modifier.height(100.dp))
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
                        append("Already have an account? ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append("Sign In")
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
                    navController.navigate(Screen.SignIn.route)
                }
            )

        }
    }
}

@Composable
fun SignUpTop() {
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

        /*Image(
            painter = painterResource(id = R.drawable.welcome_back_signup),
            contentDescription = stringResource(R.string.welcome_back),
            modifier = Modifier
                .padding(top = 40.dp)
                .height(10.dp)
                .fillMaxWidth()
        )*/

        Image(
            painter = painterResource(id = if (isSystemInDarkTheme()){
                R.drawable.signup_header_light
            } else {
                R.drawable.signup_header_black
            } ),
            contentDescription = stringResource(R.string.sign_up),
            modifier = Modifier
                .padding(top = 35.dp)
                .padding(bottom = 35.dp)
                .align(Alignment.Start)
        )

    }
}

@Composable
fun TextFieldEntries(
    textValue: String,
    labelValue: String,
    leadIcon: Painter,
    password: Boolean,
    onValueChange: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var isTextFieldFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = textValue,
        onValueChange = {text ->
            onValueChange(text)
        },
        label = {Text(text = labelValue, color = if (isTextFieldFocused || textValue.isNotEmpty()) {
            Color.Transparent // Make the label color transparent when focused
        } else {
            Color.LightGray
        })},
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .onFocusChanged { isFocused ->
                isTextFieldFocused = isFocused.isFocused
            },
        shape = RoundedCornerShape(20),
        singleLine = true,
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.surface,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = if (isSystemInDarkTheme()) {
                TextFieldBgColorDarkTheme
            } else { TextFieldBgColor },
            focusedLabelColor = Color.Transparent,
            cursorColor = PrimaryColor,
            errorBorderColor = Color.Red,
            backgroundColor = if (isSystemInDarkTheme()) {
                TextFieldBgColorDarkTheme
            } else { TextFieldBgColor },
            unfocusedLabelColor = if (isSystemInDarkTheme()) {
                Color.White
            } else { TextFieldLabelColor },
            unfocusedBorderColor = if (isSystemInDarkTheme()) {
                TextFieldBgColorDarkTheme
            } else { TextFieldBgColor }
        ),
        visualTransformation = if (!password) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                painter = leadIcon,
                contentDescription = labelValue,
                tint = Color.Gray
            )
        },
        keyboardOptions = if (password) {
            KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = if (labelValue != stringResource(id = R.string.confirm_password)){
                    ImeAction.Next
                } else { ImeAction.Done }
            )
        } else KeyboardOptions(imeAction = ImeAction.Next)
    )
}

@Composable
fun SignUpButton(state: SignUpState, onClick: () -> Unit) {
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
            text = stringResource(R.string.sign_up),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (state?.isLoading == true) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun SignInWithOAuthButton(modifier: Modifier, text: String, icon: Painter, colorFilter: ColorFilter?, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.surface,
            backgroundColor = MaterialTheme.colorScheme.background,
        ),
        shape = RoundedCornerShape(20),
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Image(
            painter = icon,
            contentDescription = "",
            modifier = modifier
                .size(40.dp)
                .padding(end = 16.dp),
            colorFilter = colorFilter
        )

        Text(
            text = text,
            color = MaterialTheme.colorScheme.surface,
            fontWeight = FontWeight.Bold
        )
    }


}


@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen(rememberNavController(), SignUpState(), {_, _ -> })
}