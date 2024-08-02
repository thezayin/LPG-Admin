package com.thezayin.lpgadmin.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.component.AdminTopBar
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.dialogs.ErrorQueryDialog
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.common.dialogs.NetworkDialog
import com.thezayin.common.viewmodel.MainViewModel
import com.thezayin.orders.presentation.FetchOrdersViewModel
import com.thezayin.orders.presentation.component.BottomCallButton
import com.thezayin.orders.presentation.component.OrderDetails
import com.thezayin.orders.presentation.component.UpdateStatus
import org.koin.compose.koinInject

@Composable
@Destination
fun OrderDetailScreen(navigator: DestinationsNavigator) {
    val viewModel: FetchOrdersViewModel = koinInject()
    val mainViewModel: MainViewModel = koinInject()

    val order = mainViewModel.orderStatus.collectAsState().value.order
    var isError = viewModel.isQueryError.collectAsState().value.isError
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    val statusList = viewModel.orderStatusList.collectAsState().value.list
    val errorMessage = viewModel.isQueryError.collectAsState().value.errorMessage

    val selectedStatus = remember { mutableStateOf(order.orderStatus!!) }
    var checkNetwork by remember { mutableStateOf(false) }
    val expandedStatus = remember { mutableStateOf(false) }
    val selectedIndex = remember { mutableIntStateOf(0) }

    GlassComponent()

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }
    if (isLoading) {
        LoadingDialog()
    }

    if (isError) {
        ErrorQueryDialog(
            showDialog = { isError = it },
            callback = { navigator.navigateUp() },
            error = errorMessage
        )
    }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = colorResource(id = com.thezayin.core.R.color.semi_transparent),
        topBar = {
            AdminTopBar(
                modifier = Modifier,
                onBackClick = { navigator.navigateUp() },
                screenTitle = "Details"
            )
        },
        bottomBar = {
            order.phoneNumber?.let { BottomCallButton(number = it) }
        }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            OrderDetails(order = order)
            UpdateStatus(
                callBack = { status ->
                    viewModel.updateStatus(order.id!!, status)
                    navigator.navigateUp()
                },
                statusList = statusList,
                selectedStatus = selectedStatus,
                expandedStatus = expandedStatus,
                selectedStatusIndex = selectedIndex
            )
        }
    }
}