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
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.common.dialogs.NetworkDialog
import com.thezayin.home.presentation.AdminHomeViewModel
import com.thezayin.home.presentation.components.AdminHomeTopBar
import com.thezayin.home.presentation.components.AdminMenu
import com.thezayin.lpgadmin.destinations.AddProductScreenDestination
import com.thezayin.lpgadmin.destinations.FetchOrdersScreenDestination
import com.thezayin.lpgadmin.destinations.ProductScreenDestination
import org.koin.compose.koinInject

@Composable
@Destination
fun HomeScreen(navigator: DestinationsNavigator) {
    val viewModel: AdminHomeViewModel = koinInject()

    var checkNetwork by remember { mutableStateOf(false) }

    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    val optionList = viewModel.adminHomeOptions.collectAsState().value.list

    if (checkNetwork) { NetworkDialog(showDialog = { checkNetwork = it }) }
    if (isLoading) { LoadingDialog() }
    GlassComponent()

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding(),
        containerColor = colorResource(id = com.thezayin.core.R.color.semi_transparent),
        topBar = {
            AdminHomeTopBar(modifier = Modifier, backClick = {
//                navigator.navigate(
//                    SettingScreenDestination
//                )
            })
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            AdminMenu(
                modifier = Modifier,
                list = optionList,
                callBack = { option ->
                    when (option.id) {
                        1 -> {
                            navigator.navigate(ProductScreenDestination)
                        }

                        2 -> {
                            navigator.navigate(AddProductScreenDestination)
                        }

                        3 -> {
                            navigator.navigate(FetchOrdersScreenDestination)
                        }
                    }
                }
            )
        }
    }
}