package com.thezayin.orders.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R

@Composable
@Preview
fun DataNotFound() {
    Box(
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 40.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = colorResource(id = R.color.semi_transparent))
    ) {
        Text(
            text = "No data found!",
            modifier = Modifier.align(Alignment.Center),
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
            fontSize = 22.sp
        )
    }
}