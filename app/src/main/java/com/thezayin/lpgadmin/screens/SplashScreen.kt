package com.thezayin.lpgadmin.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.component.GlassComponent
import com.thezayin.lpgadmin.R
import com.thezayin.lpgadmin.screens.destinations.HomeScreenDestination
import com.thezayin.lpgadmin.screens.destinations.LoginScreenDestination
import kotlinx.coroutines.delay

@SuppressLint("OpaqueUnitKey")
@Composable
@RootNavGraph(start = true)
@Destination
fun SplashScreen(navController: DestinationsNavigator) {
    GlassComponent()
    LaunchedEffect(Unit) {
        delay(5000L)
        navController.navigate(LoginScreenDestination)
    }

    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .background(color = colorResource(id = com.thezayin.core.R.color.semi_transparent))
            .fillMaxSize()
    ) {
        Text(
            text = "Safe Gas",
            fontSize = 26.sp,
            color = colorResource(id = R.color.black),
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Center)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 25.dp, vertical = 25.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 10.dp),
                color = colorResource(id = R.color.black),
                text = "Order LPG cylinders safely and conveniently with SafeGas.",
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_regular))
            )
        }
    }
}