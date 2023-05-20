package com.example.bluetoothmessanger.feature_bluetoothMessanger

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.bluetoothmessanger.feature_bluetoothMessanger.presentation.connection.component.ConnectPresentation
import com.example.bluetoothmessanger.ui.theme.BluetoothMessangerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val bluetoothManager by lazy {
        applicationContext.getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }
    private val isBluetoothEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BluetoothMessangerTheme {
                val navHostController = rememberNavController()

                val enableBluetoothLauncher = registerForActivityResult(
                    ActivityResultContracts.StartActivityForResult()
                ) { }

                val permissionsLauncher = registerForActivityResult(
                    ActivityResultContracts.RequestMultiplePermissions()
                ) { perms ->
                    val canEnableBluetooth = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        perms[Manifest.permission.BLUETOOTH_CONNECT] == true
                    } else true

                    if(canEnableBluetooth && !isBluetoothEnabled) {
                        enableBluetoothLauncher.launch(
                            Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                        )
                    }
                }

                LaunchedEffect(key1 = true) {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        permissionsLauncher.launch(
                            arrayOf(
                                Manifest.permission.BLUETOOTH_SCAN,
                                Manifest.permission.BLUETOOTH_CONNECT,
                            )
                        )
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConnectPresentation(
                        navController = navHostController
                    )
                }
            }
        }
    }
}