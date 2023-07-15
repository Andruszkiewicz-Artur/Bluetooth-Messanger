package com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection.BluetoothUiState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChatPresentation(
    state: BluetoothUiState,
    onDisconnect: () -> Unit,
    removeUserName: () -> Unit,
    editName: (String) -> Unit,
    onSendMessage: (String) -> Unit
) {
    val message = rememberSaveable {
        mutableStateOf("")
    }

    val isEditing = rememberSaveable {
        mutableStateOf(false)
    }
    val userName = rememberSaveable {
        mutableStateOf(state.userNames[state.correspondenceAddress] ?: state.correspondenceName ?: "No Name")
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
            ) {
                AnimatedContent(targetState = isEditing.value) {
                    if (it) {
                        TextField(
                            value = userName.value,
                            onValueChange = { userName.value = it },
                            modifier = Modifier
                                .weight(1f),
                            placeholder = {
                                Text(
                                    text = "New username..."
                                )
                            }
                        )
                        IconButton(onClick = {
                            isEditing.value = false
                            if ((state.userNames[state.correspondenceAddress]
                                    ?: state.correspondenceName ?: "No Name") != userName.value
                            ) {
                                editName(userName.value)
                            } else if (userName.value == "") {
                                removeUserName()
                            } else {
                                userName.value = state.userNames[state.correspondenceAddress] ?: state.correspondenceName ?: "No Name"
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Save,
                                contentDescription = "Edit Name"
                            )
                        }
                    } else {
                        Text(
                            text = state.userNames[state.correspondenceAddress] ?: state.correspondenceName ?: "No Name",
                            modifier = Modifier
                                .padding(8.dp)
                        )
                        IconButton(onClick = {
                            isEditing.value = true
                        }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit Name"
                            )
                        }
                    }
                }
            }
            IconButton(onClick = onDisconnect) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Disconnect"
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.messages) { message ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    ChatMessage(
                        message = message,
                        modifier = Modifier
                            .align(
                                if(message.isFromLocalUser) Alignment.End else Alignment.Start
                            )
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = message.value,
                onValueChange = { message.value = it },
                modifier = Modifier
                    .weight(1f),
                placeholder = {
                    Text(
                        text = "Message..."
                    )
                }
            )
            IconButton(onClick = {
                onSendMessage(message.value)
                keyboardController?.hide()
                message.value = ""
            }) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send message"
                )
            }
        }
    }
}