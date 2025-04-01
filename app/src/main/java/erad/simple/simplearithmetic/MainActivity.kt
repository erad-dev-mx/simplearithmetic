package erad.simple.simplearithmetic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import erad.simple.simplearithmetic.ui.navigation.AppNavGraph
import erad.simple.simplearithmetic.ui.theme.SimpleArithmeticTheme
import erad.simple.simplearithmetic.viewmodel.ArithmeticViewModel
import erad.simple.simplearithmetic.viewmodel.WhatIsViewModel

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
