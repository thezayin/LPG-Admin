package com.thezayin.home.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OrdersMenu(
    modifier: Modifier,
    totalNumberOfNewOrders: String,
    totalNumberOfPendingOrders: String,
    showNewOrdersBadge: Boolean,
    showPendingOrdersBadge: Boolean,
    navigateToNewOrders: () -> Unit = {},
    navigateToPendingOrders: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(vertical = 15.dp, horizontal = 25.dp)
            .fillMaxWidth()
    ) {
        NewOrder(
            modifier = Modifier,
            number = totalNumberOfNewOrders,
            showBadge = showNewOrdersBadge,
            navigate = {
                navigateToNewOrders()
            }
        )
        PendingOrders(
            modifier = Modifier,
            number = totalNumberOfPendingOrders,
            showBadge = showPendingOrdersBadge,
            navigate = {
                navigateToPendingOrders()
            }
        )
    }
}