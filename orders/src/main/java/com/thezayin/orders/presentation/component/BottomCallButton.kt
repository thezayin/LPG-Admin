package com.thezayin.orders.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R
import com.thezayin.framework.extension.functions.makeCall

@Composable
fun BottomCallButton(number: String) {
    val context = LocalContext.current
    Button(
        onClick = { context.makeCall(number) },
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 25.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.black),
        )
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Call",
                color = colorResource(id = R.color.white),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
            )
            Image(
                painter = painterResource(id = R.drawable.ic_call_white),
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp)
                    .padding(start = 5.dp)
            )
        }
    }
}


@Preview
@Composable
fun BottomCallButtonPreview() {
    BottomCallButton("1234567890")
}