package com.thezayin.orders.domain.usecase

import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import com.thezayin.orders.domain.repository.FetchOrdersRepository
import kotlinx.coroutines.flow.Flow

interface GetConfirmedOrders:suspend () -> Flow<Response<List<OrderModel>>>
class GetConfirmedOrdersImpl(private val repository: FetchOrdersRepository) : GetConfirmedOrders {
    override suspend fun invoke(): Flow<Response<List<OrderModel>>> = repository.getConfirmedOrders()
}