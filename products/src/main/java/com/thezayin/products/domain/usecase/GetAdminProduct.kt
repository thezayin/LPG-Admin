package com.thezayin.products.domain.usecase

import com.thezayin.entities.ProductModel
import com.thezayin.framework.utils.Response
import com.thezayin.products.domain.repository.AdminProductRepository
import kotlinx.coroutines.flow.Flow

interface GetAdminProduct : suspend () -> Flow<Response<List<ProductModel>>>

class GetAdminProductImpl(private val productRepository: AdminProductRepository) :
    GetAdminProduct {
    override suspend fun invoke(): Flow<Response<List<ProductModel>>> =
        productRepository.getProducts()
}