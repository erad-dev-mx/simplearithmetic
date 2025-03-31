package dev.erad.simple.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.erad.simple.R
import dev.erad.simple.ui.components.BottomNavigationBar
import dev.erad.simple.ui.screens.AboutScreen
import dev.erad.simple.ui.screens.ArithmeticScreen
import dev.erad.simple.ui.screens.OperationScreen
import dev.erad.simple.viewmodel.ArithmeticViewModel
import dev.erad.simple.viewmodel.BottomNavigationViewModel
import dev.erad.simple.viewmodel.OperationViewModel
import dev.erad.simple.viewmodel.WhatIsViewModel

sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
    data object Arithmetic : BottomNavItem("arithmetic", R.drawable.ic_home, "Home")
    data object About : BottomNavItem("about", R.drawable.ic_person, "About")
}

@Composable
fun AppNavGraph(
    modifier: Modifier,
    arithmeticViewModel: ArithmeticViewModel,
    whatIsViewModel: WhatIsViewModel,
    bottomNavigationViewModel: BottomNavigationViewModel = viewModel(),
    operationViewModel: OperationViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier,
                navController = navController,
                viewModel = bottomNavigationViewModel
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Arithmetic.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Arithmetic.route) {
                ArithmeticScreen(
                    modifier = modifier,
                    viewModel = arithmeticViewModel,
                    whatIsViewModel = whatIsViewModel,
                    onOperationClick = { operationId ->
                        navController.navigate("operation/$operationId")
                    }
                )
            }
            composable("operation/{operationId}") { backStackEntry ->
                val operationId = backStackEntry.arguments?.getString("operationId")?.toIntOrNull()
                OperationScreen(
                    modifier = modifier,
                    operationId = operationId,
                    viewModel = operationViewModel
                )
            }
            composable(BottomNavItem.About.route) {
                AboutScreen(modifier = modifier, whatIsViewModel = whatIsViewModel)
            }
        }
    }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            bottomNavigationViewModel.updateSelectedRoute(backStackEntry.destination.route ?: "")
        }
    }
}