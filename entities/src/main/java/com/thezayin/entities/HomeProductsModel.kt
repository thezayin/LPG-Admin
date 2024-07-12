package com.thezayin.entities

import android.net.Uri

data class HomeProductsModel(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val price: String? = null,
    val imageUrl: String? = null,
    val imageUri: Uri? = null,
    val date: String? = null,
)