package com.shiburagi.utility

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.widget.ImageView
import kotlin.math.pow

fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}



val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()


val Double.byte: Double
get()=this* 1024.0.pow(2.0);

