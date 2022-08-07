package com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.messager.component

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.messager.MessangerViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun Messanger_Presentation(
    navController: NavHostController,
    viewModel: MessangerViewModel = hiltViewModel()
) {

}