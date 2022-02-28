package by.akella.benefits.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.unit.ExperimentalUnitApi
import by.akella.benefits.ui.screens.BenefitsApp
import by.akella.benefits.ui.theme.BenefitsTheme

@ExperimentalUnitApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BenefitsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    BenefitsApp()
                }
            }
        }
    }
}