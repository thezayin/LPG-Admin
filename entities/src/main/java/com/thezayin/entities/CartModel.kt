package com.thezayin.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "cart_table")
@Serializable
data class CartModel(
    @PrimaryKey(autoGenerate = true)
    val _id: Int? = null,
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    var price: String? = null,
    var totalPrice: String? = "0",
    val imageUri: String? = null,
    var quantity: Int? = 1,
    val date: String? = null,
)