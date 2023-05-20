package com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection.component

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
    val state by viewModel.state.collectAsState()

    Column(

    ) {
        DevicesList(
            pairedDevices = state.pairedDevices,
            scannedDevices = state.scannedDevices
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { viewModel.onEvent(ConnectEvent.startScan) }) {
                Text(text = "Start Scan")
            }
            Button(onClick = { viewModel.onEvent(ConnectEvent.stopScan) }) {
                Text(text = "Stop Scan")
            }
        }
    }
}