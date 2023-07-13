package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.use_cases.message_use_cases

import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.MessageModel
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.repository.MessagesRepository

class GetAllUserMessagesUseCase(
    private val repository: MessagesRepository
) {

    operator fun invoke(userAddress: String): List<MessageModel> {
        return repository.getAllUserMessages(userAddress)
    }

}