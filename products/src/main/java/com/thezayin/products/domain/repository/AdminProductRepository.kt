package com.thezayin.products.domain.repository

import com.thezayin.entities.ProductModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface AdminProductRepository {
    fun getProducts(): Flow<Response<List<ProductModel>>>
}