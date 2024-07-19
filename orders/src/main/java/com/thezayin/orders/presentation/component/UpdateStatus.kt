package com.thezayin.orders.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateStatus(
    callBack: (String) -> Unit,
    statusList: List<String>,
    selectedStatus: MutableState<String>,
    expandedStatus: MutableState<Boolean>,
    selectedStatusIndex: MutableState<Int>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .padding(top = 30.dp)
    ) {
        Text(
            text = "Update Status",
            modifier = Modifier.padding(bottom = 10.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold))
        )
        ExposedDropdownMenuBox(
            expanded = expandedStatus.value,
            onExpandedChange = { expandedStatus.value = !expandedStatus.value },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextField(
                value = statusList[selectedStatusIndex.value],
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedStatus.value) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.semi_transparent),
                    unfocusedContainerColor = colorResource(id = R.color.semi_transparent),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = colorResource(id = R.color.black),
                    unfocusedTextColor = colorResource(id = R.color.black)
                )
            )
            ExposedDropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = expandedStatus.value,
                onDismissRequest = { expandedStatus.value = false }
            ) {
                statusList.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                color = colorResource(id = R.color.grey),
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                                fontWeight = if (index == selectedStatusIndex.value) FontWeight.Bold else null
                            )
                        },
                        onClick = {
                            selectedStatusIndex.value = index
                            expandedStatus.value = false
                            selectedStatus.value = item
                            callBack(item)
                        }
                    )
                }
            }
        }
    }

}