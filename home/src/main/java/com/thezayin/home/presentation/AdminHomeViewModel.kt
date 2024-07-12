package com.thezayin.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.entities.AdminOptionMenuModel
import com.thezayin.entities.GetLoadingState
import com.thezayin.framework.utils.Response
import com.thezayin.home.domain.usecase.AdminOptionMenuUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AdminHomeViewModel(
    private val adminOptionMenuUseCase: AdminOptionMenuUseCase
) : ViewModel() {
    private val _adminHomeOptions = MutableStateFlow(AdminOption())
    val adminHomeOptions = _adminHomeOptions.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    init {
        getAdminOptionMenuList()
    }

    private fun getAdminOptionMenuList() = viewModelScope.launch {
        adminOptionMenuUseCase().collect { response ->
            when (response) {
                is Response.Success -> {
                    delay(1000L)
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }

                    _adminHomeOptions.update {
                        it.copy(list = response.data)
                    }
                }

                is Response.Error -> {
                    delay(1000L)
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                }

                is Response.Loading -> {
                    _isLoading.update {
                        it.copy(isLoading = true)
                    }
                }
            }
        }
    }

    data class AdminOption(
        val list: List<AdminOptionMenuModel> = emptyList()
    )

}