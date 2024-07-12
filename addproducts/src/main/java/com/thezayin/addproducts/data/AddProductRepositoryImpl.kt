package com.thezayin.addproducts.data

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.thezayin.addproducts.domain.repository.AddProductRepository
import com.thezayin.entities.ProductModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.UUID

class AddProductRepositoryImpl(
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val refStorage: FirebaseStorage
) : AddProductRepository {
    private var operationSuccessFull = false
    override fun addProduct(
        name: String,
        description: String,
        price: String,
        date: String,
        imageUrl: String
    ): Flow<Response<Boolean>> = flow {
        operationSuccessFull = false
        try {
            emit(Response.Loading)
            auth.signInAnonymously()
            val productId = fireStore.collection("products").document().id
            val product = ProductModel(
                id = productId,
                name = name,
                description = description,
                price = price,
                imageUrl = imageUrl,
                date = date,
            )
            fireStore.collection("products").document(productId).set(product)
                .addOnSuccessListener {
                    operationSuccessFull = true
                }.await()
            if (operationSuccessFull) {
                emit(Response.Success(operationSuccessFull))
            } else {
                emit(Response.Error("unsuccessful: Try again later"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

    override fun addImage(image: Uri): Flow<Response<String>> = callbackFlow {
        try {
            trySend(Response.Loading)
            val fileName = UUID.randomUUID().toString() + ".jpg"
            refStorage.reference.child("products/$fileName")
                .putFile(image).addOnSuccessListener { taskSnapshot ->
                    trySend(Response.Success(taskSnapshot.storage.path))
                }.addOnFailureListener {
                    trySend(Response.Error(it.localizedMessage ?: it.toString()))
                }.await()
            awaitClose {
                channel.close()
            }
        } catch (e: Exception) {
            trySend(Response.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }
}