package com.thezayin.orders.domain.usecase

import com.thezayin.orders.domain.repository.OrderStatusRepository
import com.thezayin.entities.OrderStatusModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetStatusList : suspend () -> Flow<Response<List<OrderStatusModel>>>

class GetStatusListImpl(private val repository: OrderStatusRepository) :
    GetStatusList {
    override suspend fun invoke(): Flow<Response<List<OrderStatusModel>>> =
        repository.getStatus()
}