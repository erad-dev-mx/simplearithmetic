package dev.erad.simplesoftware

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import dev.erad.simplesoftware.ui.navigation.AppNavGraph
import dev.erad.simplesoftware.ui.theme.SimpleArithmeticTheme
import dev.erad.simplesoftware.viewmodel.ArithmeticViewModel
import dev.erad.simplesoftware.viewmodel.WhatIsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleArithmeticTheme {
                val viewModel = ArithmeticViewModel()
                val whatIsViewModel = WhatIsViewModel()
                AppNavGraph(
                    modifier = Modifier,
                    arithmeticViewModel = viewModel,
                    whatIsViewModel = whatIsViewModel
                )
            }
        }
    }
}
