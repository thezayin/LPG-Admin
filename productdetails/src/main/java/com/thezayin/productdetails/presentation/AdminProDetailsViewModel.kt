package com.thezayin.productdetails.presentation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.productdetails.domain.usecase.DeleteAdminProduct
import com.thezayin.productdetails.domain.usecase.UpdateAdminProduct
import com.thezayin.productdetails.domain.usecase.UpdateImage
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetLoadingState
import com.thezayin.entities.GetSuccessState
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class AdminProDetailsViewModel(
    private val deleteAdminProduct: DeleteAdminProduct,
    private val updateAdminProduct: UpdateAdminProduct,
    private val updateImage: UpdateImage
) : ViewModel() {

    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("dd/M/yyyy")
    private val currentDate: String = sdf.format(Date())

    private val _isQueryError =
        MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _isQuerySuccessful =
        MutableStateFlow(GetSuccessState())
    val isQuerySuccessful = _isQuerySuccessful.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    private val _image = MutableStateFlow(UploadImageState())
    val image = _image.asStateFlow()

    fun addImage(uri: Uri?) = viewModelScope.launch {
        _image.update {
            it.copy(image = uri)
        }
    }


    private fun addUpdatedProduct(
        id: String,
        name: String,
        description: String,
        price: String,
        currentDate: String,
        imageUrl: String
    ) = viewModelScope.launch {
        updateAdminProduct(
            id,
            name,
            description,
            price,
            currentDate,
            imageUrl
        ).collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                    _isQuerySuccessful.update {
                        it.copy(isSuccess = response.data)

                    }
                }

                is Response.Error -> {
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
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

    fun updateProduct(
        id: String,
        name: String,
        description: String,
        price: String,
    ) = viewModelScope.launch {
        image.value.image?.let {
            updateImage(it).collect { response ->
                when (response) {
                    is Response.Success -> {
                        addUpdatedProduct(
                            id,
                            name,
                            description,
                            price,
                            currentDate,
                            response.data
                        )
                    }

                    is Response.Error -> {
                        _isQueryError.update { errorState ->
                            errorState.copy(
                                isError = true,
                                errorMessage = response.e
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
    }

    fun deleteProduct(id: String) = viewModelScope.launch {
        deleteAdminProduct(id).collect { response ->
            when (response) {
                is Response.Success -> {
                    delay(8000L)
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                    _isQuerySuccessful.update {
                        it.copy(isSuccess = response.data)
                    }
                }

                is Response.Error -> {
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
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


    data class UploadImageState(
        val image: Uri? = null,
    )
}