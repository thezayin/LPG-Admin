package com.thezayin.lpgadmin.common

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.dialogs.NetworkDialog

@Destination
@Composable
fun TestingScreen(
    navigator: DestinationsNavigator
) {
    val activity = LocalContext.current as Activity
    var checkNetwork by remember { mutableStateOf(false) }

    GlassComponent()

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding(),
        containerColor = colorResource(id = com.thezayin.core.R.color.semi_transparent),
        topBar = {

        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 45.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TestingMenu(option = "Admin Panel", modifier = Modifier) {
//                navigator.navigate(AdminHomeScreenDestination)
            }
            TestingMenu(option = "User Panel", modifier = Modifier) {
//                navigator.navigate(HomeScreenDestination)
            }
        }
    }

    BackHandler {
        activity.finish()
    }
}

@Composable
fun TestingMenu(
    option: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(vertical = 5.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = colorResource(id = com.thezayin.core.R.color.semi_transparent))
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = option,
            color = colorResource(id = com.thezayin.core.R.color.black),
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}