package com.thezayin.common.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.thezayin.analytics.helpers.AnalyticsHelper
import com.thezayin.entities.OrderModel
import com.thezayin.framework.remote.RemoteConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@SuppressLint("NewApi")
class MainViewModel(
    val analyticsHelper: AnalyticsHelper,
    val remoteConfig: RemoteConfig
) : ViewModel() {
    private val _orderStatus = MutableStateFlow(GetOrderState())
    val orderStatus = _orderStatus.asStateFlow()

    fun updateOrder(order: OrderModel) {
        _orderStatus.update { it.copy(order = order) }
    }

    data class GetOrderState(val order: OrderModel = OrderModel())
}