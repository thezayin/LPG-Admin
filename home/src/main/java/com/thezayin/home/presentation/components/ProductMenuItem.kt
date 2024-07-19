package com.thezayin.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.thezayin.entities.ProductOptionModel

@Composable
fun ProductMenuItem(
    menuItem: ProductOptionModel,
    modifier: Modifier,
    callBack: (ProductOptionModel) -> Unit,
) {
    Card(
        modifier = modifier
            .width(150.dp)
            .height(150.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = com.thezayin.core.R.color.semi_transparent)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    callBack(menuItem)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = menuItem.title,
                color = colorResource(id = com.thezayin.core.R.color.black),
                fontSize = 16.sp,
                fontFamily = FontFamily(
                    Font(com.thezayin.core.R.font.noto_sans_bold)
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = menuItem.icon),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}