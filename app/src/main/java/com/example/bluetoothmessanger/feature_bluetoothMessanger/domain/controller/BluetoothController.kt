package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.controller

import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothDevice
import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val scannedDevices: StateFlow<List<BluetoothDevice>>
    val pairedDevice: StateFlow<List<BluetoothDevice>>

    fun startDiscovery()
    fun stopDiscovery()

    fun release()
}