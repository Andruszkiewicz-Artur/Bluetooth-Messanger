package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.use_cases.message_use_cases

import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.MessageModel
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.repository.MessagesRepository

class AddMessageUseCase(
    private val repository: MessagesRepository
) {

    suspend fun invoke(messageModel: MessageModel) {
        repository.addMessage(messageModel)
    }

}