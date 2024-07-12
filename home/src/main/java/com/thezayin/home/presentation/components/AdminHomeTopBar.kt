package com.thezayin.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdminHomeTopBar(
    modifier: Modifier, backClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 60.dp)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = com.thezayin.core.R.drawable.ic_menue),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    backClick()
                }
                .size(40.dp)
                .padding(10.dp),
        )
        Text(
            text = "Home",
            color = colorResource(id = com.thezayin.core.R.color.black),
            fontSize = 17.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_regular)),
        )
        Image(
            painter = painterResource(id = com.thezayin.core.R.drawable.ic_vpn),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(10.dp),
        )
    }
}