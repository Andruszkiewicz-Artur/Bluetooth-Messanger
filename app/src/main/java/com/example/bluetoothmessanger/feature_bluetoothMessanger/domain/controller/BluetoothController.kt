package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.controller

import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothDevice
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothDeviceDomain
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.util.ConnectionResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val isConnected: StateFlow<Boolean>
    val scannedDevices: StateFlow<List<BluetoothDevice>>
    val pairedDevice: StateFlow<List<BluetoothDevice>>
    val errors: SharedFlow<String>

    fun startDiscovery()
    fun stopDiscovery()
    fun release()

    fun startBluetoothServer(): Flow<ConnectionResult>
    fun connectToDevice(device: BluetoothDeviceDomain): Flow<ConnectionResult>
    fun closeConnection()
}