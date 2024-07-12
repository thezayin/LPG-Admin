package com.thezayin.productdetails.data

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.thezayin.productdetails.domain.repository.ProDetailsRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.UUID

class ProDetailsRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val refStorage: FirebaseStorage
) : ProDetailsRepository {
    private var updateSuccess = false
    private var deleteSuccess = false

    override fun updateProduct(
        id: String,
        name: String,
        description: String,
        price: String,
        date: String,
        imageUrl: String
    ): Flow<Response<Boolean>> = flow {
        updateSuccess = false
        try {
            emit(Response.Loading)
            firestore.collection("products").document(id).update(
                mapOf(
                    "name" to name,
                    "description" to description,
                    "price" to price,
                    "imageUrl" to imageUrl,
                    "date" to date
                )
            ).addOnSuccessListener {
                updateSuccess = true
            }.await()
            if (updateSuccess) {
                emit(Response.Success(updateSuccess))
            } else {
                emit(Response.Error("unsuccessful: Try again later"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: e.toString()))
        }
    }

    override fun deleteProduct(id: String): Flow<Response<Boolean>> = flow {
        deleteSuccess = true
        try {
            emit(Response.Loading)
            firestore.collection("products").document(id).delete().addOnSuccessListener {
                deleteSuccess = true
            }.await()
            if (deleteSuccess) {
                emit(Response.Success(deleteSuccess))
            } else {
                emit(Response.Error("unsuccessful: Try again later"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: e.toString()))
        }
    }

    override fun updateImage(uri: Uri): Flow<Response<String>> = callbackFlow {
        try {
            trySend(Response.Loading)
            val fileName = UUID.randomUUID().toString() + ".jpg"
            refStorage.reference.child("products/$fileName")
                .putFile(uri).addOnSuccessListener { taskSnapshot ->
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