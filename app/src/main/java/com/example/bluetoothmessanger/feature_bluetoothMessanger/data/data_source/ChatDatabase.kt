package com.example.bluetoothmessanger.feature_bluetoothMessanger.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.MessageModel
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.UserModel

@Database(
    entities = [MessageModel::class, UserModel::class],
    version = 1
)
abstract class ChatDatabase: RoomDatabase() {

    abstract val messageDao: MessageDao
    abstract val userDao: UserDao
}