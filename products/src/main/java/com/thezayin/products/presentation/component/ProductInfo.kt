package com.thezayin.products.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.thezayin.entities.HomeProductsModel

@Composable
fun ProductList(
    modifier: Modifier,
    onClick: (HomeProductsModel) -> Unit,
    product: HomeProductsModel
) {
    Box(
        modifier = modifier
            .padding(top = 20.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = colorResource(id = com.thezayin.core.R.color.semi_transparent))
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.imageUri
                    ?: com.thezayin.core.R.drawable.ic_mail,
                contentDescription = null,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(100.dp))
                    .size(60.dp)
                    .background(color = Color.White),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
            ) {
                Text(
                    text = product.name ?: "Product Name",
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                )
                Text(
                    text = product.description ?: "Product Description",
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(start = 20.dp, bottom = 20.dp, top = 20.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_edit),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(25.dp)
                        .clickable {
                            onClick(product)
                        },
                )
                Text(
                    text = "Rs: ${product.price}",
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                )
            }
        }
    }
}
