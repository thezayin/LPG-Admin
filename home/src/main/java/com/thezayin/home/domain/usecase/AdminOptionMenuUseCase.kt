package com.thezayin.home.domain.usecase

import com.thezayin.entities.AdminOptionMenuModel
import com.thezayin.framework.utils.Response
import com.thezayin.home.domain.repository.AdminOptionMenuRepository
import kotlinx.coroutines.flow.Flow

interface AdminOptionMenuUseCase : suspend () -> Flow<Response<List<AdminOptionMenuModel>>>

class AdminOptionMenuUseCaseImpl(private val repository: AdminOptionMenuRepository) :
    AdminOptionMenuUseCase {
    override suspend fun invoke(): Flow<Response<List<AdminOptionMenuModel>>> =
        repository.getAdminOptionMenuList()
}