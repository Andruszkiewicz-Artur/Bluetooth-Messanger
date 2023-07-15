package com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection

import android.os.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bluetoothmessanger.feature_bluetoothMessanger.data.mapper.toBluetoothMessage
import com.example.bluetoothmessanger.feature_bluetoothMessanger.data.mapper.toMessageModel
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.controller.BluetoothController
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothDeviceDomain
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothMessage
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.MessageModel
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.use_cases.message_use_cases.MessagesUseCases
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.use_cases.users_use_cases.UsersUseCases
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConnectViewModel @Inject constructor(
    private val bluetoothController: BluetoothController,
    private val usersUseCases: UsersUseCases,
    private val messagesUseCases: MessagesUseCases
): ViewModel() {

    private val _state = MutableStateFlow(BluetoothUiState())
    val state = combine(
        bluetoothController.scannedDevices,
        bluetoothController.pairedDevice,
        _state
    ) { scannedDevices, pairedDevices, state ->
        state.copy(
            scannedDevices = scannedDevices,
            pairedDevices = pairedDevices,
            messages = if(state.isConnected) state.messages else emptyList()
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    init {
        viewModelScope.launch {
            _state.update { it.copy(
                userNames = usersUseCases.getAllUserNamesUseCase()
            ) }
        }

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
                    .isCompleted
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
                    isConnected = false,
                    correspondenceAddress = null
                ) }
            }
            is ConnectEvent.sendMessage -> {
                viewModelScope.launch {
                    val bluetoothMessage = bluetoothController.trySendMessage(event.message)

                    if(bluetoothMessage != null) {
                        _state.update { it.copy(
                            messages = it.messages + bluetoothMessage
                        ) }

                        addMessage(bluetoothMessage)
                    }
                }
            }
        }
    }

    private fun Flow<ConnectionResult>.listen(): Job {
        return onEach { result ->
            when (result) {
                is ConnectionResult.ConnectionEstablished -> {
                    _state.update { it.copy(
                        isConnected = true,
                        isConnecting = false,
                        errorMessage = null,
                        correspondenceAddress = result.deviceAddress
                    ) }
                }
                is ConnectionResult.TransferSucceeded -> {
                    _state.update { it.copy(
                        messages = it.messages + result.message
                    ) }

                    addMessage(result.message)
                }
                is ConnectionResult.Error -> {
                    _state.update { it.copy(
                        isConnected = false,
                        isConnecting = false,
                        errorMessage = result.message,
                        correspondenceAddress = null
                    ) }
                }
            }
        }.catch {throwable ->
            _state.update { it.copy(
                isConnected = false,
                isConnecting = false,
                correspondenceAddress = null
            ) }
        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        bluetoothController.release()
    }

    private suspend fun addMessage(message: BluetoothMessage) {
        if(state.value.correspondenceAddress != null) {
            messagesUseCases.addMessageUseCase.invoke(
                message.toMessageModel(state.value.correspondenceAddress ?: "")
            )
        }
    }
}