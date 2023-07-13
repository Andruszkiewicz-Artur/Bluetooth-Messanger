package com.example.bluetoothmessanger.feature_bluetoothMessanger.domain.use_cases.users_use_cases

data class UsersUseCases(
    val getAllUserNamesUseCase: GetAllUserNamesUseCase,
    val upsertUserNameUseCase: UpsertUserNameUseCase,
    val removeUserNameUseCase: RemoveUserNameUseCase
)