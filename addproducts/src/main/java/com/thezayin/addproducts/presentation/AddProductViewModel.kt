package com.thezayin.addproducts.presentation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.addproducts.domain.usecase.AddProductUseCase
import com.thezayin.addproducts.domain.usecase.UploadImage
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

class AddProductViewModel(
    private val addProductUseCase: AddProductUseCase,
    private val uploadImage: UploadImage
) : ViewModel() {
    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("dd/M/yyyy")
    private val currentDate: String = sdf.format(Date())

    private val _isQuerySuccessful = MutableStateFlow(GetSuccessState())
    val isQuerySuccessful = _isQuerySuccessful.asStateFlow()

    private val _isQueryError = MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    private val _image = MutableStateFlow(UploadImageState())
    val image = _image.asStateFlow()

    fun updateImage(uri: Uri?) = viewModelScope.launch {
        _image.update { it.copy(image = uri) }
    }

    private fun uploadProduct(
        name: String,
        description: String,
        price: String,
        image: String,
        currentDate: String
    ) = viewModelScope.launch {
        addProductUseCase(
            name,
            description,
            price,
            image,
            currentDate
        ).collect {
            when (it) {
                is Response.Success -> {
                    delay(8000L)

                    _isQuerySuccessful.update { successState ->
                        successState.copy(isSuccess = true)
                    }

                    _isLoading.update { loadingState ->
                        loadingState.copy(isLoading = false)
                    }
                }

                is Response.Error -> {
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = it.e
                        )
                    }
                    _isLoading.update { loadingState ->
                        loadingState.copy(isLoading = false)
                    }
                }

                is Response.Loading -> {
                    _isLoading.update { loadingState ->
                        loadingState.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun addProduct(
        name: String,
        description: String,
        price: String,
    ) = viewModelScope.launch {
        uploadImage(image.value.image!!).collect {
            when (it) {
                is Response.Success -> {
                    uploadProduct(
                        name,
                        description,
                        price,
                        it.data,
                        currentDate
                    )
                }

                is Response.Error -> {
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = it.e
                        )
                    }
                    _isLoading.update { loadingState ->
                        loadingState.copy(isLoading = false)
                    }
                }

                is Response.Loading -> {
                    _isLoading.update { loadingState ->
                        loadingState.copy(isLoading = true)
                    }
                }
            }
        }
    }

    data class UploadImageState(
        val image: Uri? = null,
    )
}