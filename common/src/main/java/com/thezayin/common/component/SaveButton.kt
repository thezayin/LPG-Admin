package com.thezayin.common.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SaveButton(
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 25.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = com.thezayin.core.R.color.black),
        )
    ) {
        Text(
            text = "Save",
            color = colorResource(id = com.thezayin.core.R.color.white),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
        )
    }
}

@Preview
@Composable
fun ProductSaveButtonPreview() {
    SaveButton {}
}