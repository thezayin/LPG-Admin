package com.thezayin.home.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thezayin.entities.AdminOptionMenuModel

@Composable
fun AdminMenu(
    modifier: Modifier,
    list: List<AdminOptionMenuModel>,
    callBack: (AdminOptionMenuModel) -> Unit,
) {

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .padding(top = 25.dp),
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