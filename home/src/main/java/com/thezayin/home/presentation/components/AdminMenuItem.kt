package com.thezayin.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.entities.AdminOptionMenuModel

@Composable
fun AdminMenuItem(
    option: AdminOptionMenuModel,
    modifier: Modifier,
    callBack: (AdminOptionMenuModel) -> Unit,
) {
    Box(
        modifier = modifier
            .padding(vertical = 5.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = colorResource(id = com.thezayin.core.R.color.semi_transparent))
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                callBack(option)
            }
    ) {
        Text(
            text = option.title,
            color = colorResource(id = com.thezayin.core.R.color.black),
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}