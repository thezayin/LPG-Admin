package com.thezayin.home.domain.repository

import com.thezayin.entities.OrderMenuModel
import com.thezayin.entities.ProductOptionModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface AdminOptionMenuRepository {
    fun getProductOption(): Flow<Response<List<ProductOptionModel>>>
    fun getAdminOptionMenuList(): Flow<Response<List<OrderMenuModel>>>
}
