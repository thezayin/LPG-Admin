package com.thezayin.lpgadmin.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SaveButton
import com.thezayin.lpgadmin.screens.destinations.HomeScreenDestination

@Composable
@Destination
fun LoginScreen(navController: DestinationsNavigator) {
    val activity = LocalContext.current as Activity
    GlassComponent()
    val pin = remember { mutableStateOf(TextFieldValue()) }
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = colorResource(id = com.thezayin.core.R.color.semi_transparent),
        bottomBar = {
            SaveButton {
                if (pin.value.text == "gas@2024") {
                    navController.navigate(HomeScreenDestination)
                } else {
                    Toast.makeText(activity, "Please Enter valid Pin", Toast.LENGTH_LONG).show()
                }
            }
        }
    ) { padding ->

        Column(modifier = Modifier.padding(padding)) {
            Text(
                text = "Pin Code:",
                fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .padding(top = 150.dp)
            )
            TextField(
                value = pin.value,
                onValueChange = {
                    pin.value = it
                },
                placeholder = {
                    Text(
                        text = "Enter Product Name",
                        color = colorResource(id = com.thezayin.core.R.color.grey),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .height(55.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = com.thezayin.core.R.color.semi_transparent),
                    unfocusedContainerColor = colorResource(id = com.thezayin.core.R.color.semi_transparent),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = colorResource(id = com.thezayin.core.R.color.black),
                    unfocusedTextColor = colorResource(id = com.thezayin.core.R.color.black)
                )
            )
        }
    }
}