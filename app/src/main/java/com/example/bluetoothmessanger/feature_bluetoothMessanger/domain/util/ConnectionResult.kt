package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.util

sealed interface ConnectionResult {
    object ConnectionEstablished: ConnectionResult
    data class Error(val message: String): ConnectionResult
}