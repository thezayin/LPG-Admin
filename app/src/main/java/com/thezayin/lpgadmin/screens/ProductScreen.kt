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
import com.thezayin.lpgadmin.destinations.AddProductScreenDestination
import com.thezayin.lpgadmin.destinations.ProDetailsScreenDestination
import com.thezayin.products.presentation.AdminProductViewModel
import com.thezayin.products.presentation.component.AddProductButton
import com.thezayin.products.presentation.component.AdminProductList
import org.koin.compose.koinInject

@Composable
@Destination
fun ProductScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: AdminProductViewModel = koinInject()
    var checkNetwork by remember { mutableStateOf(false) }
    val productList = viewModel.getAdminProducts.collectAsState().value.list
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    var isError = viewModel.isQueryError.collectAsState().value.isError
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
                screenTitle = "Products"
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            AddProductButton(onClick = { navigator.navigate(AddProductScreenDestination) })
            AdminProductList(
                modifier = Modifier,
                productList = productList,
                onClick = { product ->
                    navigator.navigate(
                        ProDetailsScreenDestination(
                            id = product.id ?: "",
                            name = product.name ?: "",
                            description = product.description ?: "",
                            price = product.price ?: "",
                            imageUri = product.imageUri ?: throw IllegalArgumentException(
                                "Image Uri is null"
                            ),
                        )
                    )
                }
            )
        }
    }
}