package com.example.bluetoothmessanger.feature_bluetoothMessanger.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model.MessageModel

@Dao
interface MessageDao {

    @Query("SELECT * FROM messages WHERE userAddress LIKE :userAddress ORDER BY timeSendMessage DESC")
    fun getAllUserMessages(userAddress: String): List<MessageModel>

    @Insert()
    suspend fun addMessage(messageModel: MessageModel)

}