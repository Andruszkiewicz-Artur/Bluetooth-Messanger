package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model

typealias BluetoothDeviceDomain = BluetoothDevice

data class BluetoothDevice(
    val name: String?,
    val address: String
)
