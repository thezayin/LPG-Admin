package com.thezayin.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.products.domain.usecase.GetAdminProduct
import com.thezayin.products.domain.usecase.GetProductImages
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetLoadingState
import com.thezayin.entities.GetSuccessState
import com.thezayin.entities.HomeProductsModel
import com.thezayin.entities.ProductModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AdminProductViewModel(
    private val getAdminProduct: GetAdminProduct, private val getImages: GetProductImages
) : ViewModel() {

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    private val _getAdminProducts = MutableStateFlow(GetProductState())
    val getAdminProducts = _getAdminProducts.asStateFlow()

    private val _isQueryError = MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _isQuerySuccessful = MutableStateFlow(GetSuccessState())
    val isQuerySuccessful = _isQuerySuccessful.asStateFlow()

    init {
        getAdminProducts()
    }

    private fun getImagesFromStorage(list: List<ProductModel>) = viewModelScope.launch {
        getImages(list).collect { response ->
            when (response) {
                is Response.Loading -> {
                    _isLoading.update {
                        it.copy(isLoading = true)
                    }
                }

                is Response.Error -> {
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true, errorMessage = response.e
                        )
                    }
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                }

                is Response.Success -> {
                    _getAdminProducts.update {
                        it.copy(list = response.data)
                    }
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                }
            }
        }
    }

    private fun getAdminProducts() = viewModelScope.launch {
        getAdminProduct().collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                    getImagesFromStorage(response.data)
                }

                is Response.Error -> {
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true, errorMessage = response.e
                        )
                    }
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

    data class GetProductState(
        val list: List<HomeProductsModel> = emptyList()
    )

}