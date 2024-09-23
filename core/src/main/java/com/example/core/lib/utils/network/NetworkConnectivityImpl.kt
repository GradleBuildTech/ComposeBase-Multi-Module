package com.example.core.lib.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import javax.inject.Inject

/// Implementation of NetworkConnectivity
/// This class is used to get the connectivity manager and active network

class NetworkConnectivityImpl @Inject constructor(private val context: Context) :
    NetworkConnectivity {
    /// Get the connectivity manager
    override fun getConnectivityManager(): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    /// Get the active network
    override fun getActiveNetwork(): Network? {
        return getConnectivityManager().activeNetwork
    }

    /// Check if the internet is available
    /// This function checks if the internet is available by checking the network capabilities
    /// of the active network
    override fun isInternetAvailable(): Boolean {
        val networkCapabilities =
            getConnectivityManager().getNetworkCapabilities(getActiveNetwork())

        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }

}