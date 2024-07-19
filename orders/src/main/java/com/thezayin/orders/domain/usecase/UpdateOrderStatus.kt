package com.thezayin.orders.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.orders.domain.repository.OrderStatusRepository
import kotlinx.coroutines.flow.Flow

interface UpdateOrderStatus : suspend (String, String) -> Flow<Response<Boolean>>

class UpdateOrderStatusImpl(private val repository: OrderStatusRepository) :
    UpdateOrderStatus {
    override suspend fun invoke(id:String, status: String): Flow<Response<Boolean>> =
        repository.updateStatus(id, status)
}