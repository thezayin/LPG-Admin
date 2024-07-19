package com.thezayin.orders.domain.usecase

import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import com.thezayin.orders.domain.repository.FetchOrdersRepository
import kotlinx.coroutines.flow.Flow

interface GetDeliveredOrders:suspend () -> Flow<Response<List<OrderModel>>>

class GetDeliveredOrdersImpl(private val repository: FetchOrdersRepository) : GetDeliveredOrders {
    override suspend fun invoke(): Flow<Response<List<OrderModel>>> = repository.getDeliveredOrders()
}