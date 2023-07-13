package com.example.bluetoothmessanger.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.bluetoothmessanger.core.static.Static
import com.example.bluetoothmessanger.feature_bluetoothMessanger.data.data_source.ChatDatabase
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.controller.BluetoothController
import com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.data.AndroidBluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideBluetoothController(@ApplicationContext context: Context): BluetoothController {
        return AndroidBluetoothController(context = context)
    }

    @Provides
    @Singleton
    fun provideChatDatabase(app: Application): ChatDatabase {
        return Room.databaseBuilder(
            app,
            ChatDatabase::class.java,
            Static.DATABASE_NAME
        ).build()
    }
}