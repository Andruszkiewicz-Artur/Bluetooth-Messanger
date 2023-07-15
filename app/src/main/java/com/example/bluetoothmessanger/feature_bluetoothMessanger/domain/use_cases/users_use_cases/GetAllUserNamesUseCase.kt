package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.use_cases.users_use_cases

import androidx.compose.runtime.toMutableStateMap
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.UserModel
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.repository.UsersRepository

class GetAllUserNamesUseCase(
    private val repository: UsersRepository
) {

    operator fun invoke(): Map<String, String> {
        return repository.getAllUserNames().map {
            it.id to it.userName
        }.toMutableStateMap()
    }
}