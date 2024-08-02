package com.thezayin.orders.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thezayin.entities.OrderModel
import com.thezayin.entities.OrderStatusModel

@Composable
fun FetchOrdersList(
    modifier: Modifier,
    list: List<OrderModel>,
    callBack: (OrderModel) -> Unit,
) {

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .padding(top = 10.dp),
    ) {
        val sortedList = list.sortedByDescending { it.orderDateTime}
        items(sortedList.size) { position ->
            FetchOrdersDetails(
                order = list[position],
                callBack = { callBack(it) },
            )
        }
    }
}