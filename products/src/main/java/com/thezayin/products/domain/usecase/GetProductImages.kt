package com.thezayin.products.domain.usecase

import com.thezayin.products.domain.repository.GetProductImagesRepository
import com.thezayin.entities.HomeProductsModel
import com.thezayin.entities.ProductModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetProductImages :
    suspend (List<ProductModel>) -> Flow<Response<List<HomeProductsModel>>>

class GetProductImagesImpl(private val repository: GetProductImagesRepository) :
    GetProductImages {
    override suspend fun invoke(productList: List<ProductModel>): Flow<Response<List<HomeProductsModel>>> =
        repository.getImages(productList)
}