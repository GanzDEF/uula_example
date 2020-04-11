package dev.aisdev.example

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject


class Utils @Inject constructor(private val context: Context) {

    fun isNetworkConnected() =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)?.run {
             when {
                 Build.VERSION.SDK_INT < 23 -> activeNetworkInfo?.let {
                     it.isConnected && (it.type == ConnectivityManager.TYPE_WIFI || it.type == ConnectivityManager.TYPE_MOBILE)
                 }
                 Build.VERSION.SDK_INT >= 23 -> activeNetwork?.let {
                     getNetworkCapabilities(it)?.let { nc ->
                         nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                         || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                     }
                 }
                 else -> false
             }
        } ?: false
}