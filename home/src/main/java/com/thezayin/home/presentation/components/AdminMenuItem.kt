package com.thezayin.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.entities.OrderMenuModel

@Composable
fun AdminMenuItem(
    option: OrderMenuModel,
    modifier: Modifier,
    callBack: (OrderMenuModel) -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = colorResource(id = com.thezayin.core.R.color.semi_transparent))
            .width(150.dp)
            .height(100.dp)
            .clickable {
                callBack(option)
            }
    ) {
        Text(
            text = option.title,
            color = colorResource(id = com.thezayin.core.R.color.black),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
@Preview
fun AdminMenuItemPreview() {
    AdminMenuItem(
        option = OrderMenuModel(
            id = 1,
            title = "Order",
        ),
        modifier = Modifier,
        callBack = {}
    )
}