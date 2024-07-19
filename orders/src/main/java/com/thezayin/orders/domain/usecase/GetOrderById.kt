package com.thezayin.orders.domain.usecase

import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import com.thezayin.orders.domain.repository.FetchOrdersRepository
import kotlinx.coroutines.flow.Flow

interface GetOrderById: suspend (String) -> Flow<Response<OrderModel>>
class GetOrderByIdImpl(private val repository: FetchOrdersRepository) :GetOrderById {
    override suspend fun invoke(id: String): Flow<Response<OrderModel>> = repository.getOrderById(id)


}