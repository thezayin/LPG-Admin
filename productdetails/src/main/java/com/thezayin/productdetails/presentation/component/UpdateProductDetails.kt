package com.thezayin.productdetails.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R

@Composable
fun UpdateProductDetails(
    name: MutableState<TextFieldValue>,
    description: MutableState<TextFieldValue>,
    type: MutableState<TextFieldValue>,
    price: MutableState<TextFieldValue>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp),
    ) {
        Text(
            text = "Name:",
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.black),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
        )
        TextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            placeholder = {
                Text(
                    text = "Enter Product Name",
                    color = colorResource(id = R.color.grey),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
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
        Text(
            text = "Description:",
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.black),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
        )
        TextField(
            value = description.value,
            onValueChange = {
                description.value = it
            },
            placeholder = {
                Text(
                    text = "Enter Product Description",
                    color = colorResource(id = R.color.grey),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
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
        Text(
            text = "Type:",
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.black),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
        )
        TextField(
            value = type.value,
            onValueChange = {
                type.value = it
            },
            placeholder = {
                Text(
                    text = "Enter Product Type",
                    color = colorResource(id = R.color.grey),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
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
        Text(
            text = "Price:",
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.black),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
        )
        TextField(
            value = price.value,
            onValueChange = {
                price.value = it
            },
            placeholder = {
                Text(
                    text = "Enter Product Price",
                    color = colorResource(id = R.color.grey),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
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
    }
}

@Composable
@Preview
fun UpdateProductDetailsPreview() {
    val name = remember { mutableStateOf(TextFieldValue()) }
    val description = remember { mutableStateOf(TextFieldValue()) }
    val type = remember { mutableStateOf(TextFieldValue()) }
    val price = remember { mutableStateOf(TextFieldValue()) }

    UpdateProductDetails(
        name = name,
        description = description,
        type = type,
        price = price
    )
}