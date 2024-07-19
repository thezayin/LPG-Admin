package com.thezayin.orders.data

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import com.thezayin.orders.domain.repository.FetchOrdersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.text.SimpleDateFormat
import java.util.Date

class FetchOrdersRepositoryImpl(private val firestore: FirebaseFirestore) : FetchOrdersRepository {
    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("dd/M/yyyy")
    private val currentDate: String = sdf.format(Date())

    override fun getDeliveredOrders(): Flow<Response<List<OrderModel>>> = callbackFlow {
        try {
            trySend(Response.Loading)
            val snapshotListener =
                firestore.collection("user_orders").whereEqualTo("orderStatus", "Delivered")

                    .addSnapshotListener { snapShot, error ->
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

    override fun getConfirmedOrders(): Flow<Response<List<OrderModel>>> = callbackFlow {
        try {
            trySend(Response.Loading)
            val snapshotListener =
                firestore.collection("user_orders").whereEqualTo("orderStatus", "Confirmed")

                    .addSnapshotListener { snapShot, error ->
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

    override fun getCancelledOrders(): Flow<Response<List<OrderModel>>> = callbackFlow {
        try {
            trySend(Response.Loading)
            val snapshotListener =
                firestore.collection("user_orders").whereEqualTo("orderStatus", "Cancelled")

                    .addSnapshotListener { snapShot, error ->
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

    override fun getPendingOrders(): Flow<Response<List<OrderModel>>> = callbackFlow {
        try {
            trySend(Response.Loading)
            val snapshotListener =
                firestore.collection("user_orders").whereEqualTo("orderStatus", "In Progress")

                    .addSnapshotListener { snapShot, error ->
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

    override fun getNewOrders(): Flow<Response<List<OrderModel>>> = callbackFlow {
        try {
            trySend(Response.Loading)
            val snapshotListener =
                firestore.collection("user_orders").whereEqualTo("orderDate", currentDate)
                    .whereEqualTo("orderStatus", "In Progress")

                    .addSnapshotListener { snapShot, error ->
                        val response = if (snapShot != null) {
                            val productList = snapShot.toObjects(OrderModel::class.java)
                            Response.Success<List<OrderModel>>(productList)
                        } else {
                            Log.d("FetchOrdersRepository", "getNewOrders: ${error?.message}")
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

    override fun getOrderById(id: String): Flow<Response<OrderModel>> = callbackFlow {
        try {
            trySend(Response.Loading)
            val snapshotListener = firestore.collection("user_orders").document(id)
                .addSnapshotListener { snapShot, error ->
                    val response = if (snapShot != null) {
                        val order = snapShot.toObject(OrderModel::class.java)
                        Response.Success(order!!)
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