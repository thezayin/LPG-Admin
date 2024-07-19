package com.thezayin.orders.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.entities.CartModel
import com.thezayin.entities.OrderModel

@Composable
fun OrderDetails(
    order: OrderModel
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 35.dp, vertical = 65.dp)
    ) {
        Text(
            text = "Status",
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular))
        )
        Text(
            text = order.orderStatus!!,
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold))
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Name",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular))
        )
        Text(
            text = order.name!!,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_italic))
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Phone Number",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular))
        )
        Text(
            text = order.phoneNumber!!,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_italic))
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Address",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular))
        )
        Text(
            text = order.address!!,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_italic))
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Orders",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular))
        )
        FetchProductList(orders = order.orders!!)
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Total Price: ${order.totalAmount}",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_italic))
        )
    }
}

@Preview
@Composable
fun OrderDetailsPreview() {
    OrderDetails(
        order = OrderModel(
            orderStatus = "Delivered",
            name = "Zain Shahid",
            phoneNumber = "03033009802",
            address = "543 Arcade E-11, Islamabad, Pakistan",
            totalAmount = "12000",
            orders = listOf(
                CartModel(
                    name = "Product 1",

                    ),
                CartModel(
                    name = "Product 2",
                    quantity = 2,
                    price = "2000.0"
                ),
                CartModel(
                    name = "Product 3",
                    quantity = 3,
                    price = "3000.0"
                )
            )
        )
    )
}