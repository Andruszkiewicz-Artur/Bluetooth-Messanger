package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.use_cases.users_use_cases

import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.UserModel
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.repository.UsersRepository

class UpsertUserNameUseCase(
    private val repository: UsersRepository
) {

    suspend fun invoke(userModel: UserModel) {
        repository.upsertUserName(userModel)
    }

}