package com.thezayin.home.data

import com.thezayin.entities.OrderMenuModel
import com.thezayin.entities.ProductOptionModel
import com.thezayin.framework.utils.Response
import com.thezayin.home.domain.repository.AdminOptionMenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AdminOptionMenuRepositoryImpl : AdminOptionMenuRepository {
    override fun getAdminOptionMenuList(): Flow<Response<List<OrderMenuModel>>> = flow {
        try {
            emit(Response.Loading)
            val adminOptionMenuList = listOf(
                OrderMenuModel(1, "History"),
                OrderMenuModel(2, "Confirmed"),
                OrderMenuModel(3, "Delivered"),
                OrderMenuModel(4, "Cancelled"),
                OrderMenuModel(5, "Search Order"),
            )
            emit(Response.Success(adminOptionMenuList))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getProductOption(): Flow<Response<List<ProductOptionModel>>> = flow {
        try {
            emit(Response.Loading)
            val productOptionList = listOf(
                ProductOptionModel(1, "All Products", com.thezayin.core.R.drawable.ic_shopping_bag),
                ProductOptionModel(2, "Add Product", com.thezayin.core.R.drawable.ic_add),
            )
            emit(Response.Success(productOptionList))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}