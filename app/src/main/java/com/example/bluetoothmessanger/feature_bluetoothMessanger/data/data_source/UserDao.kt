package com.example.bluetoothmessanger.feature_bluetoothMessanger.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.UserModel

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUserNames(): List<UserModel>

    @Upsert
    suspend fun upsertUserName(userModel: UserModel)

    @Delete
    suspend fun removeUserName(userModel: UserModel)
}