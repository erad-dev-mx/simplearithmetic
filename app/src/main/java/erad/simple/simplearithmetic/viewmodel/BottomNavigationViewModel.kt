package erad.simple.simplearithmetic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BottomNavigationViewModel : ViewModel() {
    private val _selectedRoute = MutableStateFlow<String?>(null)
    val selectedRoute = _selectedRoute.asStateFlow()

    fun updateSelectedRoute(route: String) {
        viewModelScope.launch {
            _selectedRoute.emit(route)
        }
    }
}