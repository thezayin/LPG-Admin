package com.thezayin.home.domain.repository

import com.thezayin.entities.AdminOptionMenuModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface AdminOptionMenuRepository {
    fun getAdminOptionMenuList(): Flow<Response<List<AdminOptionMenuModel>>>
}
