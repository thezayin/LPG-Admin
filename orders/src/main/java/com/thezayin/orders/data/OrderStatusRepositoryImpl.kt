package com.thezayin.orders.data

import com.google.firebase.firestore.FirebaseFirestore
import com.thezayin.framework.utils.Response
import com.thezayin.orders.domain.repository.OrderStatusRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class OrderStatusRepositoryImpl(private val firestore: FirebaseFirestore) : OrderStatusRepository {
    private var updateSuccess = false
    override fun getStatus(): Flow<Response<List<String>>> = flow {

        try {
            val orderStatusModelLists = listOf(
                "Confirmed", "Delivered", "Cancelled"
            )
            emit(Response.Success(orderStatusModelLists))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }

    }

    override fun updateStatus(id: String, status: String): Flow<Response<Boolean>> = callbackFlow {
        updateSuccess = false
        try {
            trySend(Response.Loading)
            firestore.collection("user_orders").document(id).update("orderStatus", status)
                .addOnSuccessListener {
                    updateSuccess = true
                }.await()
            if (updateSuccess) {
                trySend(Response.Success(updateSuccess))
            } else {
                trySend(Response.Error("unsuccessful: Try again later"))
            }
            awaitClose { cancel() }
        } catch (e: Exception) {
            trySend(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}