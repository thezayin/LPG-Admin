package com.thezayin.orders.domain.repository

import com.thezayin.entities.OrderStatusModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface OrderStatusRepository {
    fun getStatus(): Flow<Response<List<String>>>
    fun updateStatus(id:String, status: String): Flow<Response<Boolean>>
}