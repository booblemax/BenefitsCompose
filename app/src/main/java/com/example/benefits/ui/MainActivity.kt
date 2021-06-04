package com.example.benefits.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.benefits.ui.navigation.Graph
import com.example.benefits.ui.navigation.Screens
import com.example.benefits.ui.theme.BenefitsTheme

class MainActivity : ComponentActivity() {

// pass onBackPressed callback into Graph for dispatching back events
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BenefitsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Graph(initialScreen = Screens.MAIN)
                }
            }
        }
    }
}