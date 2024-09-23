package com.example.core.lib.utils.network
import android.net.ConnectivityManager
import android.net.Network

/// Interface for NetworkConnectivity
/// This interface is used to get the connectivity manager and active network
interface NetworkConnectivity {
    fun getConnectivityManager(): ConnectivityManager
    fun getActiveNetwork(): Network?
    fun isInternetAvailable(): Boolean
}


