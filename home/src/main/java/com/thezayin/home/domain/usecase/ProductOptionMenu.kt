package com.thezayin.home.domain.usecase

import com.thezayin.entities.ProductOptionModel
import com.thezayin.framework.utils.Response
import com.thezayin.home.domain.repository.AdminOptionMenuRepository
import kotlinx.coroutines.flow.Flow

interface ProductOptionMenu : suspend () -> Flow<Response<List<ProductOptionModel>>>
class ProductOptionMenuImpl(private val repository: AdminOptionMenuRepository) : ProductOptionMenu {
    override suspend fun invoke(): Flow<Response<List<ProductOptionModel>>> =
        repository.getProductOption()
}