package com.thezayin.orders.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R

@Composable
fun SearchBar(
    text: MutableState<TextFieldValue>,
    showWarning: MutableState<Boolean>,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp, vertical = 25.dp),
    ) {
        Text(
            text = "Order ID",
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.black),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
            modifier = Modifier.padding(bottom = 15.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            TextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                },
                placeholder = {
                    Text(
                        text = "Enter order id",
                        color = colorResource(id = R.color.grey),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.semi_transparent),
                    unfocusedContainerColor = colorResource(id = R.color.semi_transparent),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = colorResource(id = R.color.black),
                    unfocusedTextColor = colorResource(id = R.color.black)
                )
            )
            if (showWarning.value) {
                Text(
                    text = "Please enter a valid number",
                    color = colorResource(id = R.color.red),
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.abeezee_italic)),
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.black),
                ),
                onClick = {
                    onClick()
                },
            ) {
                Text(
                    text = "Search",
                    color = colorResource(id = R.color.white),
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                )
            }
        }
    }
}