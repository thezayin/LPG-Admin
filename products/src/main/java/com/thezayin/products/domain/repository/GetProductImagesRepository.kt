package com.thezayin.products.domain.repository

import com.thezayin.entities.HomeProductsModel
import com.thezayin.entities.ProductModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetProductImagesRepository {
    fun getImages(productList: List<ProductModel>): Flow<Response<List<HomeProductsModel>>>
}