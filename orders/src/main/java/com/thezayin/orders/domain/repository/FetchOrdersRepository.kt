package com.thezayin.orders.domain.repository

import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface FetchOrdersRepository {
    fun getOrdersRepository(): Flow<Response<List<OrderModel>>>
    fun getOrderById(id: String): Flow<Response<OrderModel>>
    fun getPendingOrders(): Flow<Response<List<OrderModel>>>
    fun getConfirmedOrders(): Flow<Response<List<OrderModel>>>
    fun getDeliveredOrders(): Flow<Response<List<OrderModel>>>
    fun getCancelledOrders(): Flow<Response<List<OrderModel>>>
    fun getNewOrders(): Flow<Response<List<OrderModel>>>
}