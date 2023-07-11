package com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection

import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothDevice

data class BluetoothUiState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairedDevices: List<BluetoothDevice> = emptyList(),
    val isConnected: Boolean = false,
    val isConnecting: Boolean = false,
    val errorMessage: String? = null
)
