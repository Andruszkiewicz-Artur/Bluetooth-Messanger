package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model

data class BluetoothMessage(
    val message: String,
    val senderName: String,
    val isFromLocalUser: Boolean
)
