package com.waysense.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.waysense.app.ui.navigation.WaySenseApp
import com.waysense.app.ui.theme.WaySenseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaySenseTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    WaySenseApp()
                }
            }
        }
    }
}
