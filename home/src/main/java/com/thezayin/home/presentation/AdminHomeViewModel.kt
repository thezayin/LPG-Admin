package com.thezayin.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.entities.GetLoadingState
import com.thezayin.entities.OrderMenuModel
import com.thezayin.entities.ProductOptionModel
import com.thezayin.framework.utils.Response
import com.thezayin.home.domain.usecase.AdminOptionMenuUseCase
import com.thezayin.home.domain.usecase.ProductOptionMenu
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AdminHomeViewModel(
    private val adminOptionMenuUseCase: AdminOptionMenuUseCase,
    private val productOptionMenu: ProductOptionMenu
) : ViewModel() {
    private val _adminHomeOptions = MutableStateFlow(AdminOption())
    val adminHomeOptions = _adminHomeOptions.asStateFlow()

    private val _productOptions = MutableStateFlow(ProductOption())
    val productOptions = _productOptions.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    init {
        getProductOptionMenu()
        getAdminOptionMenuList()
    }

    private fun getProductOptionMenu() = viewModelScope.launch {
        productOptionMenu().collect { response ->
            when (response) {
                is Response.Success -> {
                    delay(1000L)
                    _isLoading.update { it.copy(isLoading = false) }
                    _productOptions.update { it.copy(list = response.data) }
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

    data class AdminOption(val list: List<OrderMenuModel> = emptyList())
    data class ProductOption(val list: List<ProductOptionModel> = emptyList())
}