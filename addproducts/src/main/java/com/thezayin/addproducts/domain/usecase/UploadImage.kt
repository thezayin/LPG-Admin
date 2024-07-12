package com.thezayin.addproducts.domain.usecase

import android.net.Uri
import com.thezayin.addproducts.domain.repository.AddProductRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface UploadImage : suspend (Uri) -> Flow<Response<String>>

class UploadImageImpl(private val repository: AddProductRepository) :
    UploadImage {
    override suspend fun invoke(image: Uri): Flow<Response<String>> =
        repository.addImage(image)
}