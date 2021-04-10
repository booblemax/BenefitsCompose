package com.example.benefits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.benefits.ui.theme.BenefitsTheme
import com.example.benefits.ui.screens.BenefitsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BenefitsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    BenefitsScreen()
                }
            }
        }
    }
}