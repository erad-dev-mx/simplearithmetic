package erad.simple.simplearithmetic.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WhatIsViewModel : ViewModel() {

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet: StateFlow<Boolean> = _showBottomSheet.asStateFlow()

    fun showSheet() {
        _showBottomSheet.value = true
    }

    fun hideSheet() {
        _showBottomSheet.value = false
    }
}