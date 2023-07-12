package com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection

import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothDeviceDomain

sealed class ConnectEvent {
    object startScan: ConnectEvent()
    object stopScan: ConnectEvent()
    data class onDeviceClick(val device: BluetoothDeviceDomain): ConnectEvent()
    object onStartServer: ConnectEvent()

    object disconnect: ConnectEvent()

    data class sendMessage(val message: String): ConnectEvent()
}
