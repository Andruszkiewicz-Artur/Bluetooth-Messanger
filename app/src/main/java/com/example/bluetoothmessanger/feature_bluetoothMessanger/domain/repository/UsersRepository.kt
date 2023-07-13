package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.repository

import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.UserModel

interface UsersRepository {

    fun getAllUserNames(): List<UserModel>

    suspend fun upsertUserName(userModel: UserModel)

    suspend fun removeUserName(userModel: UserModel)

}