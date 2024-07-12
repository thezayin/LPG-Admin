package com.thezayin.entities

data class GetErrorState(
    val isError: Boolean = false, val errorMessage: String = "Error"
)