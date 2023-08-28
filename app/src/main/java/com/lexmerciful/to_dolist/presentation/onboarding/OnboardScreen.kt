package com.lexmerciful.to_dolist.presentation.onboarding

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.lexmerciful.to_dolist.R
import com.lexmerciful.to_dolist.navigation.Screen
import com.lexmerciful.to_dolist.utils.OnboardingPage
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardScreen(
    navController: NavController,
    onBoardViewModel: OnBoardViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third,
    )

    val pagerState = rememberPagerState()
    val navControllerScope = rememberCoroutineScope()

    val context = LocalContext.current

    // Set up status bar color
    /*DisposableEffect(Unit) {
        val window = (context as Activity).window
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.Transparent.toArgb()

        onDispose {
            window.statusBarColor = Color.Transparent.toArgb()
        }
    }*/

    Column(modifier = Modifier.fillMaxSize()) {

            HorizontalPager(
                modifier = Modifier.weight(10f),
                count = 3,
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) {position ->
                PagerScreen(
                    onboardingPage = pages[position],
                    onNextClick = { // Handle the "Next" button click
                        if (position < pages.size - 1) {
                            navControllerScope.launch {
                                pagerState.animateScrollToPage(position + 1)
                            }
                        } else {
                            null
                        }
                    }
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .weight(1f),
                activeColor = MaterialTheme.colorScheme.primary,
                indicatorHeight = 11.dp,
                indicatorWidth = 11.dp
            )

            FinishButton(modifier = Modifier.weight(1f), pagerState = pagerState) {
                onBoardViewModel.saveOnBoardState(completed = true)
                navController.popBackStack()
                navController.navigate(Screen.SignUp.route)
            }
        }


}


@Composable
fun PagerScreen(
    onboardingPage: OnboardingPage,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier
                .padding(top = 72.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcome_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(76.dp)
                    .height(40.dp)
                    .weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.next_onboard),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
                    .clickable { onNextClick() },
                alignment = Alignment.CenterEnd,
            )
        }



        Image(
            painter = painterResource(id = if (isSystemInDarkTheme()){
                R.drawable.welcome_onboard_light
            } else {
                R.drawable.welcome_onboard_black
            } ),
            contentDescription = "Welcome",
            modifier = Modifier
                .padding(top = 80.dp)
                .height(10.dp)
                .fillMaxWidth()
        )

        Image(
            painter = painterResource(id = onboardingPage.image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.7f)
                .padding(top = 25.dp)
        )

        Image(
            painter = painterResource(id = onboardingPage.title),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        )

        Text(
            text = onboardingPage.description,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.surface,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 8.dp)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
   Row(
       modifier = modifier
           .padding(horizontal = 40.dp),
       verticalAlignment = Alignment.Top,
       horizontalArrangement = Arrangement.Center
   ){
       AnimatedVisibility(
           visible = pagerState.currentPage == 2,
           modifier = Modifier.fillMaxWidth()
       ) {
           Button(
               onClick = onClick,
               colors = ButtonDefaults.buttonColors(
                   contentColor = Color.White,
                   backgroundColor = MaterialTheme.colorScheme.primary
               ),
               shape = RoundedCornerShape(40),
               modifier = Modifier.height(40.dp)
           ) {
               Text(text = "Get Started", color = Color.White)
           }
       }
   }
}

@Preview(showBackground = true)
@Composable
fun OnBoardPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        val firstOnB = OnboardingPage.First
        PagerScreen(onboardingPage = firstOnB) {}
    }
}

@Preview
@Composable
fun SecondBoardPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
    val secondOnB = OnboardingPage.Second
    PagerScreen(onboardingPage = secondOnB) {}
    }
}

@Preview
@Composable
fun ThirdBoardPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        val thirdOnB = OnboardingPage.Third
        PagerScreen(onboardingPage = thirdOnB) {}
    }
}