package com.thezayin.productdetails.domain.repository

import android.net.Uri
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface ProDetailsRepository {
    fun updateProduct(
        id: String,
        name: String,
        description: String,
        price: String,
        date: String,
        imageUrl: String
    ): Flow<Response<Boolean>>

    fun updateImage(uri: Uri): Flow<Response<String>>

    fun deleteProduct(id: String): Flow<Response<Boolean>>
}