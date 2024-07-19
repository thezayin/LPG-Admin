package com.thezayin.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thezayin.entities.OrderMenuModel

@Composable
fun AdminMenu(
    modifier: Modifier,
    list: List<OrderMenuModel>,
    callBack: (OrderMenuModel) -> Unit,
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .padding(horizontal = 25.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(list.size) { option ->
            AdminMenuItem(
                option = list[option],
                modifier = Modifier,
                callBack = callBack,
            )
        }
    }
}