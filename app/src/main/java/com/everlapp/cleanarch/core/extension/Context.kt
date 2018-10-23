package com.everlapp.cleanarch.core.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AlertDialog

val Context.networkInfo: NetworkInfo? get() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

/**
 * Check Device is Online
 */
fun Context.isOnline(): Boolean {
    val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnectedOrConnecting
}

/**
 * TODO: Complete positive / negative buttons Actions
 */
// positiveButton: () -> Any
fun Context.showAlertDialog(message: String, title: String = "", cancelable: Boolean = false) {
    val dialog = AlertDialog.Builder(this)
    dialog.setTitle(title)
    dialog.setMessage(message)
    dialog.setPositiveButton(android.R.string.ok) {
        dialog, which -> kotlin.run {
        dialog.cancel()
    }}
    dialog.setCancelable(cancelable)
    dialog.show()
}


