package com.thezayin.orders.data

import com.google.firebase.firestore.FirebaseFirestore
import com.thezayin.orders.domain.repository.FetchOrdersRepository
import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FetchOrdersRepositoryImpl(private val firestore: FirebaseFirestore) : FetchOrdersRepository {
    override fun getOrdersRepository(): Flow<Response<List<OrderModel>>> = callbackFlow {
        try {
            trySend(Response.Loading)
            val snapshotListener =
                firestore.collection("user_orders").addSnapshotListener { snapShot, error ->
                    val response = if (snapShot != null) {
                        val productList = snapShot.toObjects(OrderModel::class.java)
                        Response.Success<List<OrderModel>>(productList)
                    } else {
                        Response.Error(error?.message ?: error.toString())
                    }
                    trySend(response).isSuccess
                }
            awaitClose {
                snapshotListener.remove()
                channel.close()
            }
        } catch (e: Exception) {
            trySend(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}