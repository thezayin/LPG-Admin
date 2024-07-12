package com.thezayin.home.data

import com.thezayin.home.domain.repository.AdminOptionMenuRepository
import com.thezayin.entities.AdminOptionMenuModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AdminOptionMenuRepositoryImpl : AdminOptionMenuRepository {
    override fun getAdminOptionMenuList(): Flow<Response<List<AdminOptionMenuModel>>> = flow {
        try {
            emit(Response.Loading)
            val adminOptionMenuList = listOf(
                AdminOptionMenuModel(1, "My Products"),
                AdminOptionMenuModel(2, "Add New Product"),
                AdminOptionMenuModel(3, "New Orders"),
                AdminOptionMenuModel(4, "Orders Delivered"),
                AdminOptionMenuModel(5, "Orders Cancelled"),
                AdminOptionMenuModel(6, "Orders History"),
            )
            emit(Response.Success(adminOptionMenuList))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}