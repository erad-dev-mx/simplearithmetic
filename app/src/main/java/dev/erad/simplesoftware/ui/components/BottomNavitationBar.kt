package dev.erad.simplesoftware.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import dev.erad.simplesoftware.ui.navigation.BottomNavItem
import dev.erad.simplesoftware.viewmodel.BottomNavigationViewModel

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier,
    viewModel: BottomNavigationViewModel
) {
    val items = listOf(
        BottomNavItem.Arithmetic,
        BottomNavItem.About
    )

    val selectedRoute by viewModel.selectedRoute.collectAsState()

    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)
    ) {
        items.forEach { item ->
            val isSelected = when (item) {
                BottomNavItem.Arithmetic -> selectedRoute == "arithmetic" || selectedRoute?.startsWith("operation/") == true
                BottomNavItem.About -> selectedRoute == "about"
            }

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    viewModel.updateSelectedRoute(item.route)
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId)
                    }
                },
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.label) },
                label = { Text(item.label) },
            )
        }
    }

    // Observe navigation changes
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            viewModel.updateSelectedRoute(backStackEntry.destination.route ?: "")
        }
    }
}