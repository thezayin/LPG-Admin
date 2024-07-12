package com.thezayin.adminorders.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.thezayin.entities.OrderStatusModel

@Composable
fun UpdateStatus(
    onDismissRequest: (OrderStatusModel) -> Unit,
    orderStatusModel: List<OrderStatusModel>
) {
    lateinit var status: OrderStatusModel
    Dialog(onDismissRequest = {
        onDismissRequest(status)
    }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(contentAlignment = Alignment.Center) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Spacer(modifier = Modifier.height(20.dp))
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(orderStatusModel.size) {
                            Text(
                                text = orderStatusModel[it].status ?: "",
                                color = colorResource(id = com.thezayin.core.R.color.black),
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { status = orderStatusModel[it] }
                            )
                        }
                    }
                }
            }
        }
    }
}