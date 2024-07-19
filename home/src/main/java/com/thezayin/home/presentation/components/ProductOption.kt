package com.thezayin.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thezayin.entities.ProductOptionModel

@Composable
fun ProductOption(
    modifier: Modifier,
    list: List<ProductOptionModel>,
    callBack: (ProductOptionModel) -> Unit,
) {
    LazyRow(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(list.size) { option ->
            ProductMenuItem(
                menuItem = list[option],
                modifier = Modifier,
                callBack = callBack,
            )
        }
    }
}