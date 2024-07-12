package com.thezayin.framework.extension.functions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.widget.Toast
import java.util.UUID


tailrec fun Context.getActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun Context.checkForInternet(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}

fun Context.makeCall() {
    val number = "03033009802"
    val intent = Intent(Intent.ACTION_DIAL)
    intent.setData(Uri.parse("tel:$number"))
    this.startActivity(intent)
}

fun Context.sendMail() {
    val i = Intent(Intent.ACTION_SEND)
    i.setType("message/rfc822")
    i.putExtra(Intent.EXTRA_EMAIL, arrayOf("zainshahidbuttt@gmail.com"))
    i.putExtra(Intent.EXTRA_SUBJECT, "subject of email")
    i.putExtra(Intent.EXTRA_TEXT, "body of email")
    try {
        startActivity(Intent.createChooser(i, "Send mail..."))
    } catch (ex: ActivityNotFoundException) {
        Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT)
            .show()
    }
}

fun Context.openLink(link: String) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(link)
    )
    this.startActivity(intent)
}

fun Context.getUserUUID(): String {
    val sharedPreferences = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    var userUUID = sharedPreferences.getString("user_uuid", null)
    if (userUUID == null) {
        userUUID = UUID.randomUUID().toString()
        sharedPreferences.edit().putString("user_uuid", userUUID).apply()
    }
    return userUUID
}
