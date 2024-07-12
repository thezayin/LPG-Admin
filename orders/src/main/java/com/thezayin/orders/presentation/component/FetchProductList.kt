package com.thezayin.adminorders.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.thezayin.entities.CartModel

@Composable
fun FetchProductList(orders: List<CartModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(orders.size) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = orders[it].name + " x " + orders[it].quantity.toString(),
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                )

                Text(
                    text = "Rs. ${orders[it].price ?: "0"}",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                    color = colorResource(id = com.thezayin.core.R.color.black)
                )
            }
        }
    }
}