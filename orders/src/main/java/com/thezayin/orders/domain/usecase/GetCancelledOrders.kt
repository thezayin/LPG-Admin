package com.thezayin.orders.domain.usecase

import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import com.thezayin.orders.domain.repository.FetchOrdersRepository
import kotlinx.coroutines.flow.Flow

interface GetCancelledOrders : suspend () -> Flow<Response<List<OrderModel>>>

class GetCancelledOrdersImpl(private val repository: FetchOrdersRepository) : GetCancelledOrders {
    override suspend fun invoke(): Flow<Response<List<OrderModel>>> =
        repository.getCancelledOrders()
}