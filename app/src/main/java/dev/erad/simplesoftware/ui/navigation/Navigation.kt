package dev.erad.simplesoftware.ui.navigation

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
import dev.erad.simplesoftware.R
import dev.erad.simplesoftware.ui.components.BottomNavigationBar
import dev.erad.simplesoftware.ui.screens.AboutScreen
import dev.erad.simplesoftware.ui.screens.ArithmeticScreen
import dev.erad.simplesoftware.ui.screens.OperationScreen
import dev.erad.simplesoftware.viewmodel.ArithmeticViewModel
import dev.erad.simplesoftware.viewmodel.BottomNavigationViewModel
import dev.erad.simplesoftware.viewmodel.OperationViewModel
import dev.erad.simplesoftware.viewmodel.WhatIsViewModel

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