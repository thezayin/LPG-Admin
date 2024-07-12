package com.thezayin.lpgadmin.common.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.messaging.FirebaseMessaging
import com.ramcosta.composedestinations.DestinationsNavHost
import com.thezayin.lpgadmin.NavGraphs
import com.thezayin.lpgadmin.common.theme.LPGAdminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                println("Token: $token")
            }
            task.result
        }
        enableEdgeToEdge()
        setContent {
            LPGAdminTheme { DestinationsNavHost(navGraph = NavGraphs.root) }
        }
    }
}