package com.thezayin.orders.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetLoadingState
import com.thezayin.entities.GetSuccessState
import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import com.thezayin.orders.domain.usecase.FetchOrders
import com.thezayin.orders.domain.usecase.GetCancelledOrders
import com.thezayin.orders.domain.usecase.GetConfirmedOrders
import com.thezayin.orders.domain.usecase.GetDeliveredOrders
import com.thezayin.orders.domain.usecase.GetNewOrders
import com.thezayin.orders.domain.usecase.GetPendingOrders
import com.thezayin.orders.domain.usecase.GetStatusList
import com.thezayin.orders.domain.usecase.UpdateOrderStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FetchOrdersViewModel(
    private val fetchOrders: FetchOrders,
    private val getStatusList: GetStatusList,
    private val updateOrderStatus: UpdateOrderStatus,
    private val getNewOrders: GetNewOrders,
    private val getPendingOrders: GetPendingOrders,
    private val getConfirmedOrders: GetConfirmedOrders,
    private val getDeliveredOrders: GetDeliveredOrders,
    private val getCancelledOrders: GetCancelledOrders,
) : ViewModel() {

    private val _userOrders = MutableStateFlow(GetUserOrdersState())
    val userOrders = _userOrders.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    private val _isQueryError =
        MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _pendingOrders = MutableStateFlow(GetPendingOrdersState())
    val pendingOrders = _pendingOrders.asStateFlow()

    private val _confirmedOrders = MutableStateFlow(GetConfirmedOrdersState())
    val confirmedOrders = _confirmedOrders.asStateFlow()

    private val _deliveredOrders = MutableStateFlow(GetDeliveredOrdersState())
    val deliveredOrders = _deliveredOrders.asStateFlow()

    private val _cancelledOrders = MutableStateFlow(GetCancelledOrdersState())
    val cancelledOrders = _cancelledOrders.asStateFlow()

    private val _newOrders = MutableStateFlow(GetNewOrdersState())
    val newOrders = _newOrders.asStateFlow()

    private val _searchOrder = MutableStateFlow(GetSearchOrderState())
    val searchOrder = _searchOrder.asStateFlow()

    private val _isQuerySuccessful =
        MutableStateFlow(GetSuccessState())
    val isQuerySuccessful = _isQuerySuccessful.asStateFlow()

    private val _orderStatusList = MutableStateFlow(GetStatusState())
    val orderStatusList = _orderStatusList.asStateFlow()

    private val _updateStatus = MutableStateFlow(UpdateStatusState())
    val updateStatus = _updateStatus.asStateFlow()

    init {
        getUserOrders()
        getOrderStatusList()
        fetchPendingOrders()
        fetchConfirmedOrders()
        fetchDeliveredOrders()
        fetchCancelledOrders()
        fetchNewOrders()
    }


    private fun fetchPendingOrders() = viewModelScope.launch {
        getPendingOrders().collect { response ->
            when (response) {
                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _pendingOrders.update { it.copy(list = response.data) }
                }

                Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun fetchConfirmedOrders() = viewModelScope.launch {
        getConfirmedOrders().collect { response ->
            when (response) {
                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _confirmedOrders.update { it.copy(list = response.data) }
                }

                Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun fetchDeliveredOrders() = viewModelScope.launch {
        getDeliveredOrders().collect { response ->
            when (response) {
                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _deliveredOrders.update { it.copy(list = response.data) }
                }

                Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun fetchCancelledOrders() = viewModelScope.launch {
        getCancelledOrders().collect { response ->
            when (response) {
                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _cancelledOrders.update { it.copy(list = response.data) }
                }

                Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun fetchNewOrders() = viewModelScope.launch {
        getNewOrders().collect { response ->
            when (response) {
                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    Log.d("neworder", "fetchNewOrders: ${response.data}")
                    _newOrders.update { it.copy(list = response.data) }
                }

                Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun getOrderStatusList() = viewModelScope.launch {
        getStatusList().collect { response ->
            when (response) {
                is Response.Error -> {
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Success -> {
                    _orderStatusList.update { it.copy(list = response.data) }
                }

                Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun updateStatus(id: String, status: String) = viewModelScope.launch {
        updateOrderStatus(id, status).collect { response ->
            when (response) {
                is Response.Error -> {
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                    _updateStatus.update { it.copy(isSuccess = false) }
                }

                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _updateStatus.update { it.copy(isSuccess = response.data) }
                    getUserOrders()
                }

                Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun getUserOrders() = viewModelScope.launch {
        fetchOrders().collect { response ->
            when (response) {
                is Response.Error -> {
                    _isQueryError.update {
                        it.copy(isError = true, errorMessage = response.e)
                    }
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                }

                is Response.Success -> {
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                    _userOrders.update {
                        it.copy(list = response.data)
                    }
                }

                Response.Loading -> {
                    _isLoading.update {
                        it.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun searchOrder(text: String) = viewModelScope.launch {
        fetchOrders().collect { response ->
            when (response) {
                is Response.Error -> {
                    _isQueryError.update {
                        it.copy(isError = true, errorMessage = response.e)
                    }
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                }

                is Response.Success -> {
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                    _searchOrder.update {
                        it.copy(list = emptyList())
                    }
                    response.data.forEach { model ->
                        if (model.id == text) {
                            _searchOrder.update {
                                it.copy(list = listOf(model))
                            }
                        }
                    }
                }

                Response.Loading -> {
                    _isLoading.update {
                        it.copy(isLoading = true)
                    }
                }
            }
        }
    }

    data class GetUserOrdersState(val list: List<OrderModel> = emptyList())
    data class GetStatusState(val list: List<String> = emptyList())
    data class UpdateStatusState(val isSuccess: Boolean = false)

    data class GetPendingOrdersState(val list: List<OrderModel> = emptyList())
    data class GetConfirmedOrdersState(val list: List<OrderModel> = emptyList())
    data class GetDeliveredOrdersState(val list: List<OrderModel> = emptyList())
    data class GetCancelledOrdersState(val list: List<OrderModel> = emptyList())
    data class GetNewOrdersState(val list: List<OrderModel> = emptyList())
    data class GetSearchOrderState(val list: List<OrderModel> = emptyList())
}