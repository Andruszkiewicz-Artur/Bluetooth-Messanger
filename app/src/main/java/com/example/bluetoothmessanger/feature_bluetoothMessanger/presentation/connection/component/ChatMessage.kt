package com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothMessage

@Composable
fun ChatMessage(
    message: BluetoothMessage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = if (message.isFromLocalUser) 15.dp else 0.dp,
                    topEnd = 15.dp,
                    bottomStart = 15.dp,
                    bottomEnd = if (message.isFromLocalUser) 0.dp else 15.dp
                )
            )
            .background(
                if (message.isFromLocalUser) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
            )
            .padding(16.dp)
    ) {
        Text(
            text = message.senderName,
            fontSize = 10.sp,
            color = if (message.isFromLocalUser) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = message.message,
            color = if (message.isFromLocalUser) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .widthIn(max = 250.dp)
        )
    }
}