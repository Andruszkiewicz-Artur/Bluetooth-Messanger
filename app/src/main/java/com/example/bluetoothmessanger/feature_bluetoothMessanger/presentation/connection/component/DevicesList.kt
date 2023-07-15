package com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothDevice
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothDeviceDomain
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.UserModel
import com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection.ConnectEvent

@Composable
fun DevicesList(
    pairedDevices: List<BluetoothDevice>,
    scannedDevices: List<BluetoothDevice>,
    usersNames: Map<String, String>,
    onClick: (BluetoothDeviceDomain) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item{
            Text(
                text = "Paired devices",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        }

        items(pairedDevices) {device ->
            Text(
                text = usersNames[device.address] ?: device.name ?: "No name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        onClick(device)
                    }
            )
        }

        item{
            Text(
                text = "Scanned devices",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
        }

        items(scannedDevices) {device ->
            Text(
                text = usersNames[device.address] ?: device.name ?: "No name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        onClick(device)
                    }
            )
        }
    }
}