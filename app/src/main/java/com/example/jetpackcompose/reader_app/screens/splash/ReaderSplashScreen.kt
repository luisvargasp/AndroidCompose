package com.example.jetpackcompose.reader_app.screens.splash

import android.view.animation.OvershootInterpolator
import android.widget.Space
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.jetpackcompose.R
import com.example.jetpackcompose.reader_app.navigation.ReaderScreens
import com.example.jetpackcompose.weather_app.navigation.WeatherScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun  ReaderSplashScreen(navController: NavController ) {
    val scale= remember {
        Animatable(0f)

    }
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(1f,
            animationSpec =   tween(durationMillis = 2000, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )
        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navController.navigate(ReaderScreens.LoginScreen.name)
        }else {
            navController.navigate(ReaderScreens.HomeScreen.name)
        }



    })

    Surface(modifier = Modifier
        .padding(15.dp)
        .size(330.dp)
        .scale(scale.value)

        , shape = CircleShape, color = Color.White, border =
        BorderStroke(width = 2.dp, color = Color.LightGray)
    ) {

        Column (modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            ReaderLogo()
            Spacer(modifier=Modifier.height(15.dp))
            Text(text = "Read. Change. Yourself", style = MaterialTheme.typography.headlineMedium, color = Color.LightGray)
        }


    }
}

@Composable
 fun ReaderLogo(modifier: Modifier=Modifier) {
    Text(modifier=modifier.padding(bottom = 16.dp),
        text = "A. Reader",
        style = MaterialTheme.typography.headlineLarge,
        color = Color.Red.copy(alpha = 0.5f)
    )
}