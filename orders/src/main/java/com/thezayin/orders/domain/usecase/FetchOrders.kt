package com.thezayin.orders.domain.usecase

import com.thezayin.orders.domain.repository.FetchOrdersRepository
import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface FetchOrders :
    suspend () -> Flow<Response<List<OrderModel>>>

class FetchOrdersImpl(private val repository: FetchOrdersRepository) :
    FetchOrders {
    override suspend fun invoke(): Flow<Response<List<OrderModel>>> =
        repository.getOrdersRepository()
}