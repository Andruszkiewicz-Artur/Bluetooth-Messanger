package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.util

import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothMessage

sealed interface ConnectionResult {
    object ConnectionEstablished: ConnectionResult
    data class TransferSucceeded(val message: BluetoothMessage): ConnectionResult
    data class Error(val message: String): ConnectionResult
}