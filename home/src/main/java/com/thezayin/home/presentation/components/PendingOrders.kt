package com.thezayin.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PendingOrders(
    modifier: Modifier,
    showBadge: Boolean = false,
    number: String,
    navigate: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .padding(start = 10.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = colorResource(id = com.thezayin.core.R.color.semi_transparent))
            .width(150.dp)
            .height(200.dp)
            .clickable {
                navigate()
            }
    ) {
        BadgedBox(
            badge = {
                if (showBadge) {
                    Badge { Text(text = number) }
                }
            }, modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Pending Orders",
                color = colorResource(id = com.thezayin.core.R.color.black),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
@Preview
fun PendingOrdersPreview() {
    PendingOrders(
        modifier = Modifier,
        showBadge = true,
        number = "10",
        navigate = {}
    )
}