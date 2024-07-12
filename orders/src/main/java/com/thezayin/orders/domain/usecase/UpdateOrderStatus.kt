package com.thezayin.orders.domain.usecase

import com.thezayin.orders.domain.repository.OrderStatusRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface UpdateOrderStatus : suspend (String) -> Flow<Response<Boolean>>

class UpdateOrderStatusImpl(private val repository: OrderStatusRepository) :
    UpdateOrderStatus {
    override suspend fun invoke(status: String): Flow<Response<Boolean>> =
        repository.updateStatus(status)
}