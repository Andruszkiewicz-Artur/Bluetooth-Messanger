package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Messages")
data class MessageModel(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int? = null,

    @ColumnInfo(name = "message")
    val message: String,

    @ColumnInfo(name = "userAddress")
    val userAddress: String,

    @ColumnInfo(name = "isFromLocalUser")
    val isFromLocalUser: Boolean
)
