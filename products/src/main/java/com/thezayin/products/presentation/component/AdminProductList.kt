package com.thezayin.products.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thezayin.entities.HomeProductsModel

@Composable
fun AdminProductList(
    modifier: Modifier,
    onClick: (HomeProductsModel) -> Unit,
    productList: List<HomeProductsModel>
) {

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .padding(top = 10.dp),
    ) {
        items(productList.size) { product ->
            ProductList(
                modifier = modifier,
                onClick = onClick,
                product = productList[product]
            )
        }
    }
}