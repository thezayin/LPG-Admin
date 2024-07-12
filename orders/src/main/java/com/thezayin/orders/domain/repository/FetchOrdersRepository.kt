package com.thezayin.orders.domain.repository

import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface FetchOrdersRepository {
    fun getOrdersRepository(): Flow<Response<List<OrderModel>>>
}