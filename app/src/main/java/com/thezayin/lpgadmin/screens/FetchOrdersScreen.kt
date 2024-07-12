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
import com.thezayin.adminorders.presentation.FetchOrdersViewModel
import com.thezayin.adminorders.presentation.component.FetchOrdersList
import com.thezayin.adminorders.presentation.component.UpdateStatus
import com.thezayin.common.component.AdminTopBar
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.dialogs.ErrorQueryDialog
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.common.dialogs.NetworkDialog
import org.koin.compose.koinInject

@Composable
@Destination
fun FetchOrdersScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: FetchOrdersViewModel = koinInject()
    var checkNetwork by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    var isError = viewModel.isQueryError.collectAsState().value.isError
    val errorMessage = viewModel.isQueryError.collectAsState().value.errorMessage
    val list = viewModel.userOrders.collectAsState().value.list
    val statusList = viewModel.orderStatusList.collectAsState().value.list
    val showDialog = remember { mutableStateOf(false) }

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

    if (showDialog.value) {
        UpdateStatus(onDismissRequest = {
            viewModel.updateStatus(it.status!!)
            showDialog.value = false
        }, orderStatusModel = statusList)
    }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = colorResource(id = com.thezayin.core.R.color.semi_transparent),
        topBar = {
            AdminTopBar(
                modifier = Modifier,
                onBackClick = { navigator.navigateUp() },
                screenTitle = "Orders"
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            FetchOrdersList(
                modifier = Modifier,
                list = list,
                onClick = { showDialog.value = true }
            )
        }
    }
}