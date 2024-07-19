package com.thezayin.lpgadmin.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.thezayin.lpgadmin.screens.destinations.OrderDetailScreenDestination
import com.thezayin.orders.presentation.FetchOrdersViewModel
import com.thezayin.orders.presentation.component.FetchOrdersList
import org.koin.compose.koinInject

@Composable
@Destination
fun DeliveredOrdersScreen(navigator: DestinationsNavigator) {
    val viewModel: FetchOrdersViewModel = koinInject()
    val mainViewModel: MainViewModel = koinInject()

    var checkNetwork by remember { mutableStateOf(false) }

    val list = viewModel.deliveredOrders.collectAsState().value.list
    var isError = viewModel.isQueryError.collectAsState().value.isError
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    val errorMessage = viewModel.isQueryError.collectAsState().value.errorMessage

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
                screenTitle = "New Orders"
            )
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            FetchOrdersList(
                modifier = Modifier,
                list = list,
                callBack = { orderModel ->
                    mainViewModel.updateOrder(orderModel)
                    navigator.navigate(OrderDetailScreenDestination)
                },
            )
        }
    }

}