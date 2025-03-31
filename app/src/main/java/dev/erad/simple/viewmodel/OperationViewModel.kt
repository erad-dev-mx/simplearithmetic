package dev.erad.simple.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.sqrt

class OperationViewModel : ViewModel() {
    private val _num1 = MutableStateFlow("")
    val num1 = _num1.asStateFlow()

    private val _num2 = MutableStateFlow("")
    val num2 = _num2.asStateFlow()

    private val _result = MutableStateFlow<Double?>(null)
    val result = _result.asStateFlow()

    fun onNum1Change(value: String) {
        _num1.value = when {
            value.isEmpty() -> "0"
            value.length > 5 -> _num1.value
            value.toDoubleOrNull() == null -> _num1.value
            value.startsWith("0") && value.length > 1 -> value.trimStart('0')
            else -> value
        }
    }

    fun onNum2Change(value: String) {
        _num2.value = when {
            value.isEmpty() -> "0"
            value.length > 5 -> _num2.value
            value.toDoubleOrNull() == null -> _num2.value
            value.startsWith("0") && value.length > 1 -> value.trimStart('0')
            else -> value
        }
    }

    fun calculate(operation: (Double, Double) -> Double) {
        val a = _num1.value.toDoubleOrNull()
        val b = _num2.value.toDoubleOrNull()

        if (a != null && b != null) {
            _result.value = operation(a, b)
        }
    }

    fun calculateSquareRoot() {
        val a = _num1.value.toDoubleOrNull()
        if (a != null) {
            _result.value = sqrt(a)
        }
    }

    fun resetValues() {
        _num1.value = "0"
        _num2.value = "0"
        _result.value = null
    }
}