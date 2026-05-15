package com.example.madhu_marga

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.madhu_marga.ui.MadhuMargaApp
import com.example.madhu_marga.ui.theme.MadhuMargaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MadhuMargaTheme {
                MadhuMargaApp()
            }
        }
    }
}
