package com.thezayin.productdetails.domain.usecase

import android.net.Uri
import com.thezayin.productdetails.domain.repository.ProDetailsRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface UpdateImage : suspend (Uri) -> Flow<Response<String>>
class UpdateImageImpl(private val repository: ProDetailsRepository) :
    UpdateImage {
    override suspend fun invoke(uri: Uri): Flow<Response<String>> = repository.updateImage(uri)
}