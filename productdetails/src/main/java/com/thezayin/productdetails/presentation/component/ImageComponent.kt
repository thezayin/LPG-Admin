package com.thezayin.productdetails.presentation.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thezayin.core.R

@Composable
fun ImageComponent(
    oldUri: Uri?,
    imageUri: Uri?,
    onImageSelected: () -> Unit = {},
    imageSelected: MutableState<Boolean>?,
) {
    Card(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 25.dp),
        colors = CardDefaults.cardColors(
            colorResource(id = R.color.semi_transparent)
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(150.dp))
                    .size(150.dp)
                    .background(colorResource(id = R.color.light_purple))
                    .clickable {
                        onImageSelected()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = if (imageSelected!!.value) imageUri else oldUri,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}