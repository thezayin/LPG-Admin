package com.thezayin.orders.domain.usecase

import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import com.thezayin.orders.domain.repository.FetchOrdersRepository
import kotlinx.coroutines.flow.Flow

interface GetPendingOrders : suspend () -> Flow<Response<List<OrderModel>>>

class GetPendingOrdersImpl(private val repository: FetchOrdersRepository) : GetPendingOrders {
    override suspend fun invoke(): Flow<Response<List<OrderModel>>> = repository.getPendingOrders()
}