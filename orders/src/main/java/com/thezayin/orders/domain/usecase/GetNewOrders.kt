package com.thezayin.orders.domain.usecase

import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import com.thezayin.orders.domain.repository.FetchOrdersRepository
import kotlinx.coroutines.flow.Flow

interface GetNewOrders : suspend () -> Flow<Response<List<OrderModel>>>

class GetNewOrdersImpl(private val repository: FetchOrdersRepository) : GetNewOrders {
    override suspend fun invoke(): Flow<Response<List<OrderModel>>> = repository.getNewOrders()
}