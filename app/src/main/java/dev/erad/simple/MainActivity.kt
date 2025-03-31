package dev.erad.simple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import dev.erad.simple.ui.navigation.AppNavGraph
import dev.erad.simple.ui.theme.SimpleArithmeticTheme
import dev.erad.simple.viewmodel.ArithmeticViewModel
import dev.erad.simple.viewmodel.WhatIsViewModel

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
