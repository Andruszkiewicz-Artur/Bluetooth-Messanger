package com.example.bluetoothmessanger.feature_bluetoothMessanger.data.mapper

import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.BluetoothMessage
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.MessageModel
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.UserModel

fun String.toBluetoothMessage(isFromLocalUser: Boolean): BluetoothMessage {
    val name = substringBeforeLast("#")
    val message = substringAfterLast("#")

    return BluetoothMessage(
        message = message,
        senderName = name,
        isFromLocalUser = isFromLocalUser
    )
}

fun BluetoothMessage.toByteArray(): ByteArray {
    return "$senderName#$message".encodeToByteArray()
}

fun BluetoothMessage.toMessageModel(userAddress: String): MessageModel {
    return MessageModel(
        id = null,
        message = this.message,
        userAddress = userAddress,
        isFromLocalUser = this.isFromLocalUser
    )
}