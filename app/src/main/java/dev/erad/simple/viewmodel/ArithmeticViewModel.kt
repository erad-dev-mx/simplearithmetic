package dev.erad.simple.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import dev.erad.simple.R
import dev.erad.simple.model.DrawableStringModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArithmeticViewModel : ViewModel() {

    private val _title = MutableStateFlow(R.string.title_greetings)
    val title: StateFlow<Int> = _title.asStateFlow()

    private val _importantPeople = MutableStateFlow(
        listOf(
            DrawableStringModel(R.drawable.important_people_1, R.string.important_people_1),
            DrawableStringModel(R.drawable.important_people_2, R.string.important_people_2),
            DrawableStringModel(R.drawable.important_people_3, R.string.important_people_3),
            DrawableStringModel(R.drawable.important_people_4, R.string.important_people_4),
            DrawableStringModel(R.drawable.important_people_5, R.string.important_people_5),
            DrawableStringModel(R.drawable.important_people_6, R.string.important_people_6),
        )
    )
    val importantPeople = _importantPeople.asStateFlow()

    private val _selectedPerson = MutableStateFlow<DrawableStringModel?>(null)
    val selectedPerson = _selectedPerson.asStateFlow()

    fun selectPerson(person: DrawableStringModel) {
        _selectedPerson.value = person
    }

    fun clearSelection() {
        _selectedPerson.value = null
    }

    fun getDescriptionResourceId(@StringRes textResourceId: Int): Int {
        return when (textResourceId) {
            R.string.important_people_1 -> R.string.important_people_1_text
            R.string.important_people_2 -> R.string.important_people_2_text
            R.string.important_people_3 -> R.string.important_people_3_text
            R.string.important_people_4 -> R.string.important_people_4_text
            R.string.important_people_5 -> R.string.important_people_5_text
            R.string.important_people_6 -> R.string.important_people_6_text
            else -> R.string.important_people_1_text
        }
    }

    private val _operations = MutableStateFlow(
        listOf(
            DrawableStringModel(R.drawable.operation, R.string.operation_addition),
            DrawableStringModel(R.drawable.operation, R.string.operation_subtraction),
            DrawableStringModel(R.drawable.operation, R.string.operation_multiplication),
            DrawableStringModel(R.drawable.operation, R.string.operation_division),
            DrawableStringModel(R.drawable.operation, R.string.operation_exponentiation),
            DrawableStringModel(R.drawable.operation, R.string.operation_modulo),
            DrawableStringModel(R.drawable.operation, R.string.operation_square_root)
        )
    )
    val operations = _operations.asStateFlow()
}