package com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection

import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothDevice
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothMessage

data class BluetoothUiState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairedDevices: List<BluetoothDevice> = emptyList(),
    val userNames: Map<String, String> = emptyMap(),
    val isConnected: Boolean = false,
    val isConnecting: Boolean = false,
    val errorMessage: String? = null,
    val messages: List<BluetoothMessage> = emptyList(),
    val correspondenceAddress: String? = null,
    val correspondenceName: String? = null
)
