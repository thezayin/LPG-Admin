package com.thezayin.lpgadmin.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.common.dialogs.NetworkDialog
import com.thezayin.home.presentation.AdminHomeViewModel
import com.thezayin.home.presentation.components.AdminHomeTopBar
import com.thezayin.home.presentation.components.AdminMenu
import com.thezayin.home.presentation.components.OrdersMenu
import com.thezayin.home.presentation.components.ProductOption
import com.thezayin.lpgadmin.screens.destinations.AddProductScreenDestination
import com.thezayin.lpgadmin.screens.destinations.CancelledOrdersScreenDestination
import com.thezayin.lpgadmin.screens.destinations.ConfirmedOrdersScreenDestination
import com.thezayin.lpgadmin.screens.destinations.DeliveredOrdersScreenDestination
import com.thezayin.lpgadmin.screens.destinations.FetchOrdersScreenDestination
import com.thezayin.lpgadmin.screens.destinations.NewOrderScreenDestination
import com.thezayin.lpgadmin.screens.destinations.PendingOrdersScreenDestination
import com.thezayin.lpgadmin.screens.destinations.ProductScreenDestination
import com.thezayin.lpgadmin.screens.destinations.SearchOrderScreenDestination
import com.thezayin.orders.presentation.FetchOrdersViewModel
import org.koin.compose.koinInject

@Composable
@Destination
fun HomeScreen(navigator: DestinationsNavigator) {
    val viewModel: AdminHomeViewModel = koinInject()
    val ordersViewModel: FetchOrdersViewModel = koinInject()

    var checkNetwork by remember { mutableStateOf(false) }

    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    val optionList = viewModel.adminHomeOptions.collectAsState().value.list
    val productOptionList = viewModel.productOptions.collectAsState().value.list

    val newOrders = ordersViewModel.newOrders.collectAsState().value.list
    val pendingOrders = ordersViewModel.pendingOrders.collectAsState().value.list

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }
    if (isLoading) {
        LoadingDialog()
    }
    GlassComponent()

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = colorResource(id = com.thezayin.core.R.color.semi_transparent),
        topBar = {
            AdminHomeTopBar(modifier = Modifier, backClick = {})
        },
    ) { padding ->

        Column(modifier = Modifier.padding(padding)) {
            Text(
                text = "Products Control:",
                fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .padding(top = 40.dp)
            )

            ProductOption(list = productOptionList, modifier = Modifier) { menu ->
                when (menu.id) {
                    1 -> navigator.navigate(ProductScreenDestination)
                    2 -> navigator.navigate(AddProductScreenDestination)
                }
            }
            Text(
                text = "Order Control:",
                fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .padding(top = 20.dp)
            )

            OrdersMenu(
                modifier = Modifier,
                totalNumberOfNewOrders = newOrders.size.toString(),
                totalNumberOfPendingOrders = pendingOrders.size.toString(),
                showNewOrdersBadge = newOrders.isNotEmpty(),
                showPendingOrdersBadge = pendingOrders.isNotEmpty(),
                navigateToNewOrders = {
                    navigator.navigate(NewOrderScreenDestination)
                },
                navigateToPendingOrders = {
                    navigator.navigate(PendingOrdersScreenDestination)
                }
            )

            AdminMenu(modifier = Modifier, list = optionList, callBack = {
                when (it.id) {
                    1 -> navigator.navigate(FetchOrdersScreenDestination)
                    2 -> navigator.navigate(ConfirmedOrdersScreenDestination)
                    3 -> navigator.navigate(DeliveredOrdersScreenDestination)
                    4 -> navigator.navigate(CancelledOrdersScreenDestination)
                    5 -> navigator.navigate(SearchOrderScreenDestination)
                }
            })
        }
    }
}

