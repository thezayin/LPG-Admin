package com.thezayin.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "profile_table")
@Serializable
data class ProfileModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String? = null,
    val phoneNumber: String? = null,
    var address: String? = null,
    var area: String? = null,
    var city: String? = null,
    var email: String? = null,
)