package com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection

sealed class ConnectEvent {
    object startScan: ConnectEvent()
    object stopScan: ConnectEvent()
}
