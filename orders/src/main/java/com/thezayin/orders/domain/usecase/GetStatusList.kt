package com.thezayin.orders.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.orders.domain.repository.OrderStatusRepository
import kotlinx.coroutines.flow.Flow

interface GetStatusList : suspend () -> Flow<Response<List<String>>>

class GetStatusListImpl(private val repository: OrderStatusRepository) :
    GetStatusList {
    override suspend fun invoke(): Flow<Response<List<String>>> =
        repository.getStatus()
}