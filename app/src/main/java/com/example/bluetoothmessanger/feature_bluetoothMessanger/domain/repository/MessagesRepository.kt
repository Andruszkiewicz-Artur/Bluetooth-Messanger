package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.repository

import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.MessageModel

interface MessagesRepository {

    fun getAllUserMessages(userAddress: String): List<MessageModel>

    suspend fun addMessage(messageModel: MessageModel)

}