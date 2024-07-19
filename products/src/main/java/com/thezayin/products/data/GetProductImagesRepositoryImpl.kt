package com.thezayin.products.data

import com.google.firebase.storage.FirebaseStorage
import com.thezayin.entities.HomeProductsModel
import com.thezayin.entities.ProductModel
import com.thezayin.framework.utils.Response
import com.thezayin.products.domain.repository.GetProductImagesRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class GetProductImagesRepositoryImpl(private val storage: FirebaseStorage) :
    GetProductImagesRepository {
    private val list = mutableListOf<HomeProductsModel>()
    override fun getImages(productList: List<ProductModel>): Flow<Response<List<HomeProductsModel>>> =
        callbackFlow {
            trySend(Response.Loading)
            productList.forEach { product ->
                product.imageUrl?.let { it ->
                    storage.reference.child(it)
                        .downloadUrl.addOnSuccessListener { uri ->
                            list.add(
                                HomeProductsModel(
                                    id = product.id,
                                    name = product.name,
                                    description = product.description,
                                    price = product.price,
                                    imageUrl = product.imageUrl,
                                    imageUri = uri,
                                    date = product.date
                                )
                            )
                        }.addOnFailureListener {
                            trySend(Response.Error(it.localizedMessage ?: it.toString()))
                        }.await()
                }
            }
            trySend(Response.Success(list))
            awaitClose()
        }
}