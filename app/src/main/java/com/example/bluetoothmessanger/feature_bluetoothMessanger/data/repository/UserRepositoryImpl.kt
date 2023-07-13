package com.example.bluetoothmessanger.feature_bluetoothMessanger.data.repository

import com.example.bluetoothmessanger.feature_bluetoothMessanger.data.data_source.UserDao
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.UserModel
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.repository.UsersRepository

class UsersRepositoryImpl(
    private val dao: UserDao
): UsersRepository {
    override fun getAllUserNames(): List<UserModel> {
        return dao.getAllUserNames()
    }

    override suspend fun upsertUserName(userModel: UserModel) {
        dao.upsertUserName(userModel)
    }

    override suspend fun removeUserName(userModel: UserModel) {
        dao.removeUserName(userModel)
    }
}