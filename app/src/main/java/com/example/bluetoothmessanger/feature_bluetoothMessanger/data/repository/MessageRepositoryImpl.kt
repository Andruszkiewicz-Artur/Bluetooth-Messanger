package com.example.bluetoothmessanger.feature_bluetoothMessanger.data.repository

import com.example.bluetoothmessanger.feature_bluetoothMessanger.data.data_source.MessageDao
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.MessageModel
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.repository.MessagesRepository

class MessageRepositoryImpl(
    private val dao: MessageDao
): MessagesRepository {
    override fun getAllUserMessages(userAddress: String): List<MessageModel> {
        return dao.getAllUserMessages(userAddress)
    }

    override suspend fun addMessage(messageModel: MessageModel) {
        return dao.addMessage(messageModel)
    }
}