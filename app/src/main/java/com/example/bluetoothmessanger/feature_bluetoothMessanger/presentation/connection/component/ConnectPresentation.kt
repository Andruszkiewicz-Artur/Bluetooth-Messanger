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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection.ConnectViewModel

@Composable
fun ConnectPresentation(
    navController: NavHostController,
    viewModel: ConnectViewModel = hiltViewModel()
) {
    val state = viewModel.state

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

        items(state.value.pairedDevices) {device ->
            Text(
                text = device.name ?: "No name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {

                    }
            )
        }

        item{
            Text(
                text = "Scanned devices",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        }

        items(state.value.pairedDevices) {device ->
            Text(
                text = device.name ?: "No name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {

                    }
            )
        }
    }
}