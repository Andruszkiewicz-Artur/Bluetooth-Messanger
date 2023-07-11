package com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.controller.BluetoothController
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothDeviceDomain
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.util.ConnectionResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ConnectViewModel @Inject constructor(
    private val bluetoothController: BluetoothController
): ViewModel() {

    private val _state = MutableStateFlow(BluetoothUiState())
    val state = combine(
        bluetoothController.scannedDevices,
        bluetoothController.pairedDevice,
        _state
    ) { scannedDevices, pairedDevices, state ->
        state.copy(
            scannedDevices = scannedDevices,
            pairedDevices = pairedDevices
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    init {
        bluetoothController.isConnected.onEach { isConnected ->
            _state.update { it.copy(
                isConnected = isConnected
            ) }
        }.launchIn(viewModelScope)

        bluetoothController.errors.onEach { error ->
            _state.update { it.copy(
                errorMessage = error
            ) }
        }.launchIn(viewModelScope)
    }

    private var deviceConnectionJob: Job? = null

    fun onEvent(event: ConnectEvent) {
        when (event) {
            ConnectEvent.startScan -> {
                bluetoothController.startDiscovery()
            }
            ConnectEvent.stopScan -> {
                bluetoothController.stopDiscovery()
            }

            is ConnectEvent.onDeviceClick -> {
                _state.update { it.copy(
                    isConnecting = true
                ) }
                bluetoothController
                    .connectToDevice(event.device)
                    .listen()
            }
            ConnectEvent.onStartServer -> {
                _state.update { it.copy(
                    isConnecting = true
                ) }
                deviceConnectionJob = bluetoothController
                    .startBluetoothServer()
                    .listen()
            }

            ConnectEvent.disconnect -> {
                deviceConnectionJob?.cancel()
                bluetoothController.closeConnection()
                _state.update { it.copy(
                    isConnecting = false,
                    isConnected = false
                ) }
            }
        }
    }

    private fun Flow<ConnectionResult>.listen(): Job {
        return onEach { result ->
            when (result) {
                ConnectionResult.ConnectionEstablished -> {
                    _state.update { it.copy(
                        isConnected = true,
                        isConnecting = false,
                        errorMessage = null
                    ) }
                }
                is ConnectionResult.Error -> {
                    _state.update { it.copy(
                        isConnected = false,
                        isConnecting = false,
                        errorMessage = result.message
                    ) }
                }
            }
        }.catch {throwable ->
            _state.update { it.copy(
                isConnected = false,
                isConnecting = false
            ) }
        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        bluetoothController.release()
    }
}