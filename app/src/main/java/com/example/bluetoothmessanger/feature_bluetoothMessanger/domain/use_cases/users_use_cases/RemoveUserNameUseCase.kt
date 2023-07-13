package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.use_cases.users_use_cases

import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.UserModel
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.repository.UsersRepository

class RemoveUserNameUseCase(
    private val repository: UsersRepository
) {

    suspend operator fun invoke(userModel: UserModel) {
        repository.removeUserName(userModel)
    }

}