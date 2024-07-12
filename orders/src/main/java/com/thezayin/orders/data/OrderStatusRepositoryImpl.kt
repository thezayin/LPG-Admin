package com.thezayin.orders.data

import com.google.firebase.firestore.FirebaseFirestore
import com.thezayin.orders.domain.repository.OrderStatusRepository
import com.thezayin.entities.OrderStatusModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class OrderStatusRepositoryImpl(private val firestore: FirebaseFirestore) : OrderStatusRepository {
    private var updateSuccess = false
    override fun getStatus(): Flow<Response<List<OrderStatusModel>>> = flow {

        try {
            val orderStatusModelLists = listOf(
                OrderStatusModel("1", "Pending"),
                OrderStatusModel("2", "Order Confirmed"),
                OrderStatusModel("3", "Order Dispatched"),
                OrderStatusModel("4", "Order Delivered"),
                OrderStatusModel("5", "Order Cancelled")
            )
            emit(Response.Success(orderStatusModelLists))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }

    }

    override fun updateStatus(status: String): Flow<Response<Boolean>> = callbackFlow {
        updateSuccess = false
        try {
            trySend(Response.Loading)
            firestore.collection("user_orders").document(status).update("status", status)
                .addOnSuccessListener {
                    updateSuccess = true
                }.await()
            if (updateSuccess) {
                trySend(Response.Success(updateSuccess))
            } else {
                trySend(Response.Error("unsuccessful: Try again later"))
            }
        } catch (e: Exception) {
            trySend(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}