package com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection.component

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection.ConnectEvent
import com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection.ConnectViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ConnectPresentation(
    navController: NavHostController,
    viewModel: ConnectViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = state.errorMessage) {
        state.errorMessage?.let { message ->
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(key1 = state.isConnected) {
        if (state.isConnected) {
            Toast.makeText(
                context,
                "You`re connected!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    when {
        state.isConnecting -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
                Text(text = "Connecting...")
            }
        }
        state.isConnected -> {
            ChatPresentation(
                state = state,
                onDisconnect = {
                    viewModel.onEvent(ConnectEvent.disconnect)
                },
                onSendMessage = {
                    viewModel.onEvent(ConnectEvent.sendMessage(it))
                },
                removeUserName = {
                    viewModel.onEvent(ConnectEvent.removeName)
                },
                editName = {
                    viewModel.onEvent(ConnectEvent.setNewName(it))
                }
            )
        }
        else -> {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                DevicesList(
                    pairedDevices = state.pairedDevices,
                    scannedDevices = state.scannedDevices,
                    usersNames = state.userNames,
                    onClick = { device ->
                        viewModel.onEvent(ConnectEvent.onDeviceClick(device))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { viewModel.onEvent(ConnectEvent.startScan) }) {
                        Text(
                            text = "Start Scan",
                            fontSize = 10.sp
                        )
                    }
                    Button(onClick = { viewModel.onEvent(ConnectEvent.stopScan) }) {
                        Text(
                            text = "Stop Scan",
                            fontSize = 10.sp
                        )
                    }
                    Button(onClick = { viewModel.onEvent(ConnectEvent.onStartServer) }) {
                        Text(
                            text = "Start Server",
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}