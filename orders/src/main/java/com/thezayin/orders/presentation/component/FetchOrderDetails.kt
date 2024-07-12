package com.thezayin.adminorders.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.entities.OrderModel

@Composable
fun FetchOrdersDetails(
    order: OrderModel?,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = colorResource(id = com.thezayin.core.R.color.semi_transparent))
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .heightIn(min = 250.dp, max = 700.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 25.dp, bottom = 5.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Column(
                    modifier = Modifier.padding(start = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = order?.orderTime + " " + order?.orderDate,
                            color = colorResource(id = com.thezayin.core.R.color.black),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                        )
                        Card(
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .clickable {
                                    onClick()
                                },
                            shape = RoundedCornerShape(40.dp),
                            colors = CardDefaults.cardColors(
                                colorResource(id = com.thezayin.core.R.color.black)
                            )
                        ) {
                            Text(
                                text = order?.orderStatus ?: "pending",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                                color = colorResource(id = com.thezayin.core.R.color.white),
                                modifier = Modifier.padding(
                                    vertical = 4.dp, horizontal = 12.dp
                                )
                            )
                        }
                    }

                    Text(
                        text = order?.name ?: "name",
                        color = colorResource(id = com.thezayin.core.R.color.black),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                    )


                    Text(
                        text = order?.phoneNumber ?: "03011001111",
                        color = colorResource(id = com.thezayin.core.R.color.black),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                    )

                    Text(
                        text = order?.address ?: "Islamabad",
                        color = colorResource(id = com.thezayin.core.R.color.black),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                    )
                    Text(
                        text = "Orders",
                        color = colorResource(id = com.thezayin.core.R.color.black),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_italic)),
                    )
                    FetchProductList(orders = order!!.orders!!)
                }
            }
            Row(
                modifier = Modifier.padding(top = 25.dp),
            ) {
                Text(
                    text = "Total: ${order?.totalAmount ?: "0"}Rs.",
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                )
            }
            Row(
                modifier = Modifier.padding(bottom = 25.dp, top = 15.dp),
            ) {
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(40.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = com.thezayin.core.R.color.black)
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
                            color = colorResource(id = com.thezayin.core.R.color.white),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                        )
                        Image(
                            painter = painterResource(id = com.thezayin.core.R.drawable.ic_call_white),
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                                .padding(start = 5.dp)
                        )
                    }
                }
            }
        }
    }
}
