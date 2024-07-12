package com.thezayin.common.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun LoadingDialog() {
    Dialog(onDismissRequest = { }) {
        Card(
            colors = CardDefaults.cardColors(containerColor = colorResource(id = com.thezayin.core.R.color.semi_transparent)),
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Please wait....",
                        fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_regular)),
                        color = colorResource(id = com.thezayin.core.R.color.black),
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 40.dp)
                            .padding(bottom = 20.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = colorResource(id = com.thezayin.core.R.color.black))
                    }
                }
            }
        }
    }
}